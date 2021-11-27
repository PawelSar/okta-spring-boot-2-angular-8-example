import { Component } from '@angular/core';
import { CalendarOptions, DateSelectArg, EventClickArg, EventApi } from '@fullcalendar/angular';
import { INITIAL_EVENTS, createEventId } from './event-utils';
import { GoogleCalendarService } from '../shared/calendar/google-calendar.service';
import { EventInput } from '@fullcalendar/angular';
import { Calendar, createElement, DayHeaderContentArg } from '@fullcalendar/core';
import interactionPlugin from '@fullcalendar/interaction';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';

@Component({
  selector: 'app-google-calendar',
  templateUrl: './google-calendar.component.html',
  styleUrls: ['./google-calendar.component.scss']
})

export class GoogleCalendarComponent {
event: any = {};
meetings: Array<any>;


  constructor(private googleCalendarService: GoogleCalendarService) {
  }


  calendarVisible = true;
  calendarOptions: CalendarOptions = {
  themeSystem: 'bootstrap',
    headerToolbar: {
      left: 'dayGridMonth,timeGridWeek,timeGridDay listWeek',
      center: 'title',
      right: 'prevYear,prev,next,nextYear'
    },

//     customButtons: {
//             custom1: {
//               text: 'custom 1',
//               click: function() {
//                 alert('clicked custom button 1!');
//               }
//             },

//     buttonIcons: {
//   prev: 'left-single-arrow',
//   next: 'fa-chevron-right',
//   prevYear: 'left-double-arrow',
//   nextYear: 'right-double-arrow'
// },


    buttonText: {
     today:    'Dzisiaj',
      month:    'Miesiąc',
      week:     'Tydzień',
      day:      'Dzień',
      list:     'Plan'
    },

    height: '850px',
    nowIndicator: true,

//     bootstrapFontAwesome: {
//
//       close: 'fa-times',
//       prev: 'fa-chevron-right',
//       next: 'fa-chevron-right',
//       prevYear: 'fa-angle-double-right',
//       nextYear: 'fa-angle-double-right'
//
//     },


    initialView: 'dayGridMonth',
    initialEvents: this.getEvents(), // alternatively, use the `events` setting to fetch from a feed
    //initialEvents: INITIAL_EVENTS,
    weekends: true,
    editable: true,
    selectable: true,
    selectMirror: true,
    dayMaxEvents: true,
    firstDay: 1,
    locale: 'pl',
    select: this.handleDateSelect.bind(this),
    eventClick: this.handleEventClick.bind(this),
    eventsSet: this.handleEvents.bind(this)
    /* you can update a remote database when these fire:
    eventAdd:
    eventChange:
    eventRemove:
    */
  };
  currentEvents: EventApi[] = [];

  handleCalendarToggle() {
    this.calendarVisible = !this.calendarVisible;
  }

  handleWeekendsToggle() {
    const { calendarOptions } = this;
    calendarOptions.weekends = !calendarOptions.weekends;
  }

  handleDateSelect(selectInfo: DateSelectArg) {
    const title = prompt('Dodaj event');
    const calendarApi = selectInfo.view.calendar;
    calendarApi.unselect(); // clear date selection

    if (title) {
      calendarApi.addEvent({
        id: createEventId(),
        title,
        start: selectInfo.startStr,
        end: selectInfo.endStr,
        allDay: selectInfo.allDay
      });

     this.addEventTask(title, selectInfo);
    }
  }

    addEventTask(title: string, selectInfo: DateSelectArg) {
      this.googleCalendarService.addEvent(title, selectInfo).subscribe(result => {
      }, error => console.error(error));
    }

      getEvents() {
      console.log("get events triggered");
      var NAMES = [];

          this.googleCalendarService.getEvents().subscribe(result => {

           this.meetings = result;


  for (const meeting of this.meetings) {


      let newName = {
              id: meeting.id,
             title: meeting.summary,
             start: meeting.start.timeZone,
             end: meeting.end.timeZone

      };

      console.log(newName);
      NAMES.push(newName);
      }
          }, error => console.error(error));
let y1 = NAMES as EventInput[];

          return y1;

}
  handleEventClick(clickInfo: EventClickArg) {
    if (confirm(`Are you sure you want to delete the event '${clickInfo.event.title}'`)) {
      clickInfo.event.remove();
    }
  }

  handleEvents(events: EventApi[]) {
    this.currentEvents = events;
  }
}
//
// document.addEventListener('DOMContentLoaded', function() {
//   let calendarEl: HTMLElement = document.getElementById('calendar')!;
//
//   class CustomDayHeader extends Component<{ text: string }> {
//     render() {
//       return createElement('div', {}, '!' + this.props.text + '!')
//     }
//   }
//
//   let calendar = new Calendar(calendarEl, {
//     plugins: [ interactionPlugin, dayGridPlugin, timeGridPlugin, listPlugin ],
//     headerToolbar: {
//       left: 'prev,next today',
//       center: 'title',
//       right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
//     },
//     initialDate: '2018-01-12',
//     navLinks: true, // can click day/week names to navigate views
//     editable: true,
//     dayMaxEvents: true, // allow "more" link when too many events
//     dayHeaderContent(arg: DayHeaderContentArg) {
//       return createElement(CustomDayHeader, { text: arg.text })
//     },
//     events: getEvents(),
//   });
//
//   calendar.render();
// });
//
// function getEvents() {
//
//
//
//       console.log("get events2 triggered");
//       var NAMES = [];
// var googleCalendarService : GoogleCalendarService;
//           GoogleCalendarService.getEvents().subscribe(result => {
//
//   for (const meeting of result) {
//
//       let newName = {
//               id: meeting.id,
//              title: meeting.summary,
//              start: new Date().toISOString().replace(/T.*$/, '')
//       };
//       NAMES.push(newName);
//       }
//           }, error => console.error(error));
// let y1 = NAMES as EventInput[];
//
//           return y1;
//
// }
