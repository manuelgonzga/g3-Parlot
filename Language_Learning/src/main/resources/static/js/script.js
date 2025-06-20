const randomId = () => {
    let dt = new Date().getTime();
    const uuid = "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, (c) => {
        const r = (dt + Math.random() * 16) % 16 | 0;
        dt = Math.floor(dt / 16);
        return (c == "x" ? r : (r & 0x3) | 0x8).toString(16);
    });
    return uuid;
};

let selected_hours = [];
const dayNamesShort = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
const hotTime = [5, 6, 7, 8, 9, 13, 14, 15, 16];

const pad = (n) => (n >= 10 ? n : "0" + n);
const date = new Date();
const d = date.getDate();
const m = date.getMonth() + 1;
const y = date.getFullYear();

let selectedTimeSlots = [];

const getDifferentMinBetweenTime = (startDate, endDate) => {
    const startTime = startDate.getTime();
    const endTime = endDate.getTime();
    const diffTime = endTime - startTime;
    return Math.floor((diffTime / 1000 / 60) << 0);
};
const calendarEl = document.getElementById("js-book-calendar");

const eventDidMount = (args) => {
    const { event, el } = args;
    console.log(args);
    const data = {
        ...event.extendedProps,
        id: event.id,
        start: event.start,
        end: event.end
    };
    el.setAttribute("tabindex", -1);
    if (!args.isPast && ![...el.classList].includes("booked-slot")) {
        $(el).tooltip({
            html: true,
            title: `
                    <p class="mg-b-0 tx-nowrap">Your time: ${moment(
                event.extendedProps?.TeacherStart ?? new Date()
            ).format("DD/MM/YYYY hh:mm A")}</p>
                      <p class="mg-b-0 tx-nowrap">VN time: ${moment(
                event.start
            ).format("DD/MM/YYYY hh:mm A")}</p>
                `,
            animation: false,
            template: `<div class="tooltip" role="tooltip">
                    <div class="tooltip-arrow">
                    </div>
                    <div class="tooltip-inner">
                    
                    </div>
                  </div>`,
            trigger: "hover"
        });
    }

    let diff = getDifferentMinBetweenTime(new Date(), event.start);
    let cancelable = diff > 60 ? true : false;
    !!el &&
    [...el.classList].includes('booked-slot') &&
    $(el)
        .popover({
            html: true,
            container: 'body',
            trigger: 'focus',
            title: 'Booked information',
            content: `  
                <p class="mg-b-5"><span class="mg-r-5">Course:</span><span class="tx-medium">${event
                .extendedProps.bookInfo?.DocumentName ?? ''}</span></p>
                <p class="mg-b-5"><span class="mg-r-5">Lesson:</span><span class="tx-medium">${event
                .extendedProps.bookInfo?.LessonName ?? ''}</span></p>
                <p class="mg-b-5"><span class="mg-r-5">Student:</span><span class="tx-medium">${event
                .extendedProps.bookInfo?.name ?? ''}</span></p>
                <p class="mg-b-5"><span class="mg-r-5">Your time:</span><span class="tx-medium">${moment(
                event.extendedProps?.TeacherStart ?? new Date(),
            ).format('DD/MM/YYYY hh:mm A')}</span></p>
                <p class="mg-b-0"><span class="mg-r-5">VN time:</span><span class="tx-medium">${moment(
                event.start,
            ).format('DD/MM/YYYY hh:mm A')}</span></p>
                `,
        })
        .on('click', function() {
            $(this).popover('show');
        });
    $(document).on('click', function(event) {
        let $el = $(el);
        if (
            !$(event.target).closest($el).length &&
            !$(event.target).closest('.popover').length
        ) {
            $el.popover('hide');
        }
    });
    const events = calendar.getEvents();
    const dayHeaders = document.querySelectorAll(".fc-col-header-cell");
    // console.log({dayHeaders});
    if (dayHeaders.length > 0)
        for (let i = 0; i < dayHeaders.length; i++) {
            //  console.log(dayHeaders[i]);
            if ("data-date" in dayHeaders[i].dataset) continue;
            const date = dayHeaders[i].getAttribute("data-date");
            const dateHD = new Date(date);
            let bookedSlot = 0;
            let totalSlot = 0;
            events.map((event) => {
                let eventDate = new Date();
                if (event.extendedProps && event.extendedProps.Start) {
                    eventDate = new Date(event.extendedProps.Start.split("T")[0]);
                }
                if (eventDate.getTime() === dateHD.getTime()) {
                    (event.extendedProps.available === true ||
                        event.extendedProps.bookStatus === true) &&
                    totalSlot++;
                    event.extendedProps.bookStatus === true && bookedSlot++;
                }
            });
            // console.log(dayHeaders[i]);
            // console.log({bookedSlot, totalSlot});
            dayHeaders[i].querySelector(".booked").textContent = bookedSlot;
            dayHeaders[i].querySelector(".total").textContent = totalSlot;
        }
};

const eventClick = (args) => {
    console.log(
        "event Source calendar",
        calendar.getEventSources()[0].internalEventSource.meta
    );
    const element = args.el;
    const { start, end, id } = args.event;
    if (
        !!$toggleCheckbox &&
        $toggleCheckbox.prop("checked") === true &&
        ![...element.classList].includes("booked-slot")
    ) {
        toast.warning(
            'Please uncheck "Only show student booking hours" before open or booking slot !!',
            {
                position: toast.POSITION.TOP_CENTER,
                autoClose: 5000
            }
        );
        return;
    }
    if (
        [...element.classList].includes("fc-event-past") ||
        ![...element.classList].includes("empty-slot")
    )
        return;
};

calendar = new FullCalendar.Calendar(calendarEl, {
    height: 550,
    expandRows: true,
    slotMinTime: "10:00",
    slotMaxTime: "23:00",
    viewDidMount: (args) => {
        console.log(args);
    },
    headerToolbar: {
        start: "", // will normally be on the left. if RTL, will be on the right
        center: "",
        end: "today,prev,title,next" // will normally be on the right. if RTL, will be on the left
    },
    titleFormat: { year: "numeric", month: "short" },
    navLinks: true, // can click day/week names to navigate views
    editable: false,
    stickyHeaderDates: true,
    selectable: true,
    nowIndicator: true,
    allDaySlot: false,
    dayMaxEvents: true, // allow "more" link when too many events
    eventOverlap: false,
    initialDate: new Date(),
    initialView: "timeGridWeek",
    firstDay: 1,
    slotDuration: "01:00",
    slotLabelInterval: "01:00",
    slotEventOverlap: false,
    selectOverlap: function (event) {
        return event.rendering === "background";
    },
    slotLabelContent: function (arg) {
        const hour = arg.date.getHours();

        let templateEl = document.createElement("div");
        templateEl.setAttribute("class", "slot-label");
        const html = `
            ${moment(arg.date).format("HH:mm")}(UTC+2)
            `;
        templateEl.innerHTML = html;
        return { html };
    },

    dayHeaderContent: function (args) {
        const days = args.date.getDay();
        const d = args.date.getDate();

        const html = `
                    <div class="header-container">
                        <div class="date-wrap">
                            <span class="hd-date">${d} </span><span class="hd-day">${dayNamesShort[days]}</span>
                        </div>
                       <div class="box-slot">
                            <span class="booked"></span> <span class="mg-x-2">/</span> <span class="total"></span>
                       </div>
                    </div>
                `;
        return { html };
    },
    eventClassNames: function (args) {
        const { event, isPast, isStart } = args;
        const {
            bookInfo,
            eventType,
            bookStatus,
            available,
            isEmptySlot
        } = event.extendedProps;
        let classLists = bookStatus ? "booked-slot" : "available-slot";
        classLists += eventType === 1 ? " hot-slot " : "";
        classLists += isEmptySlot ? " empty-slot" : "";
        return classLists;
    },
    eventContent: function (args) {
        let templateEl = document.createElement("div");
        const { event, isPast, isStart } = args;
        const {
            bookInfo,
            eventType,
            bookStatus,
            available,
            isEmptySlot
        } = event.extendedProps;
        let name = "";
        if (!!bookInfo) {
            let arrayName = bookInfo.name.split(" ");
            name = arrayName[arrayName.length - 1];
        }
        const data = {
            ...event.extendedProps,
            id: event.id,
            start: event.start,
            end: event.end
        };
        const html = `
                ${
            !isEmptySlot
                ? `
                <div class="inner-book-wrap ">
                    <div class="inner-content">
                    ${
                    bookStatus
                        ? `
                            <span class="label-book booked"><i class="fas ${
                            isPast ? "fa-check" : "fa-user-graduate"
                        }"></i> ${
                            isPast ? "FINISHED" : "BOOKED"
                        } ${name}</span>
                            `
                        : available ? `<span class="label-book"><i class="fas fa-copyright"></i>AVAILABLE</span>` : ''
                }
                    </div>
                </div>`
                : ""
        }
            `;
        templateEl.innerHTML = html;
        return { domNodes: [templateEl] };
    },
    eventClick: eventClick,
    eventDidMount: eventDidMount,
    nowIndicatorDidMount: function (args) {
        //   console.log("nowIndicatorDidMount", args);
    },
    select: function(info) {

        const previousEvent = calendar.getEventById('selected-slot');

        // Si existe, lo elimina
        if (previousEvent) {
            previousEvent.remove();
        }

        const start = info.startStr; // La hora de inicio de la franja horaria seleccionada
        const end = info.endStr; // La hora de finalización de la franja horaria seleccionada
        selected_hours = [];
        selected_hours.push({ start, end });
        calendar.addEvent({
            id: 'selected-slot',
            title: 'Seleccionado',
            start: start,
            end: end,
            color: '#d3d3d3', // Cambia esto al color que prefieras
        });
    },
});

// Assuming your URL is something like 'http://example.com/profesor/123'
let url = new URL(window.location.href); // Get the current URL
let pathSegments = url.pathname.split('/'); // Split the path into segments
let profesorId = parseInt(pathSegments[pathSegments.length - 1]); // Get the last segment

fetch(`/api/horario/reservadas/${profesorId}`)
    .then(response => response.json())
    .then(data => {
        // Guardar las franjas horarias reservadas en la variable reservedTimeSlots
        reservedTimeSlots = data;
        reservedTimeSlots.forEach(slot => {
            calendar.addEvent({
                id: slot.id,
                title: 'Reservado',
                start: slot.start,
                rendering: 'background',
                color: 'grey',
            });
        });
    })
    .catch((error) => {
        console.error('Error:', error);
    });

calendar.render();

console.log(calendar.getEventSources(0));



// Primero, obtén el botón de submit por su ID o clase
let submitButton = document.getElementById('reservation-submit'); // reemplaza 'submit-button-id' con el ID real de tu botón

// Luego, agrega un event listener para el evento de click
submitButton.addEventListener('click', function(event) {

    // Previene el comportamiento por defecto del botón submit, que es recargar la página
    event.preventDefault();

    // Aquí puedes agregar el código que quieres que se ejecute cuando el botón es clickeado
    let url = new URL(window.location.href); // Get the current URL
    let pathSegments = url.pathname.split('/'); // Split the path into segments
    let profesorId = parseInt(pathSegments[pathSegments.length - 1]); // Get the last segment
    const hora = selected_hours[0];

    // Obtén la hora de inicio de la franja horaria seleccionada
    const horaInicio = selected_hours[0].start;

// Conviértela a un objeto de tipo Date
    const fechaHoraInicio = new Date(horaInicio);

// Obtén la fecha actual
    const fechaActual = new Date();

// Compara las fechas para determinar si el día ya ha pasado
    if (fechaHoraInicio < fechaActual) {
        alert('Fecha seleccionada ya pasada.');
        return;
    }else {
        // Envía la franja horaria al servidor
        fetch(`/api/horario/${profesorId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(hora)
        })
            .then(() => {
                // Redirige al usuario a la página confirmarClase.html después de enviar la solicitud
                window.location.href = `/confirmar-clase/profesor/${profesorId}`;
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }
});

// $('body').on(
// 	'click',
// 	'#js-book-calendar .fc-next-button',
// 	triggerNextCalendar,
// );
// $('body').on(
// 	'click',
// 	'#js-book-calendar .fc-prev-button',
// 	triggerPrevCalendar,
// );
// $('body').on(
// 	'click',
// 	'#js-book-calendar .fc-today-button',
// 	triggerTodayCalendar,
// );

$toggleCheckbox = $("#student-toggle-checkbox");

$("body").on("change", $toggleCheckbox, showStudentToggle);

function showStudentToggle() {
    const value = $toggleCheckbox.prop("checked");
    const nonBookedEvents = $(".fc-event:not(.booked-slot)");
    value
        ? nonBookedEvents.addClass("hide-event")
        : nonBookedEvents.removeClass("hide-event");
}