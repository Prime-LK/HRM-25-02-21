<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<html lang="en">
<head>
	<%@include file="../../WEB-INF/jsp/head.jsp"%>
	
	<link href="<c:url value='/resources/hrm/css/CalenderPicker.css'/>" rel="stylesheet">	
	<link href="/resources/hrm/css/fullcalendar.css" rel="stylesheet" />
	<link href="/resources/hrm/css/fullcalendar.print.css" rel="stylesheet" />
	
<style>

#external-events p input {
	margin: 0;
	vertical-align: middle;
}

#calendar {
	/* 		float: right; */
	margin: 0 auto;
	width: 900px;
	background-color: #FFFFFF;
	border-radius: 6px;
	box-shadow: 0 1px 2px #C3C3C3;
}
</style>
	
</head>
<body>
	<div class="wrapper">
		<div class="main-header">
			<!-- Logo Header -->
				<%@include file="../../WEB-INF/jsp/logoHeader.jsp"%>
			<!-- End Logo Header -->
			<!-- Navbar Header -->
				<%@include file="../../WEB-INF/jsp/navbar.jsp"%>
			<!-- End Navbar -->
		</div>
		<!-- Sidebar -->
			<%@include file="../../WEB-INF/jsp/slideBar.jsp"%>
		<!-- End Sidebar -->
		<div class="main-panel">
			<div class="content">
				<div class="panel-header bg-primary-gradient">
					<div class="page-inner py-3">
						<div class="d-flex align-items-left align-items-md-center flex-column flex-md-row">
							<div class="col-xl col-lg">
								 <h2 class="text-white pb-2 fw-bold">Create Calendar</h2>
							</div>

						</div>
					</div>
				</div>
				
				<div class="page-inner mt--5">	
					<div class="container-fluid">

			              <div class="card shadow mb-4 border-left-primary">
			                <div class="card-body">
			                
								<div class="row">
									<div class="col-xl col-md-6 mb-4">

					                
									<form:form action="saveCalander" method="POST"
										onSubmit="return validateForm()" id="calander"
										modelAttribute="calander">


										<div id='wrap'>

											<div id='calendar'></div>

											<div style='clear: both'></div>
										</div>

										<div class=" form-group row">
											<div class="col-lg-4">
												<label>Start date</label>
												<form:input type="date" class="form-control form-control-sm" id="pickYear" path="" required="true" />
											</div>
											<div class="col-lg-4">
												<label>End Date</label>
												<form:input type="date" class="form-control form-control-sm" id="endYear" path=""  required="true" />
											</div>
										</div>
										<div class=" form-group row">
											<div class="col-lg">
												<button id="btndate" value="Create Year" onclick="load()" class="btn btn-success btn-sm">Create Year</button>
											</div>
										</div>
										<div class="row" hidden="true">
											<div class="col-9">
												<div class="form-group row">
													<table id="year" path="date">
														<tr>
															<td>date</td>
														</tr>
														<tr>

														</tr>
													</table>
												</div>
											</div>
										</div>
										<br>
										<div id="app"></div>
									</form:form>

									</div>
									<div class="col-xl col-md-6 mb-4">
									

																			
									</div>
								</div>

			                </div>
			              </div>
			            
					</div>	
				</div>
				
			</div>	
			<%@include file="../../WEB-INF/jsp/footer.jsp"%>			
		</div>
	</div>
<%@include file="../../WEB-INF/jsp/commJs.jsp"%>

<script src="<c:url value='/resources/hrm/js/Calander.js'/>"></script>
<script src="<c:url value='/resources/hrm/js/CalenderPicker.js'/>"></script>

<script src="/resources/hrm/js/jquery-1.10.2.js"
	type="text/javascript"></script>
<script src="/resources/hrm/js/jquery-ui.custom.min.js"
	type="text/javascript"></script>
<script src="/resources/hrm/js/fullcalendar.js"
	type="text/javascript"></script>

<script>
	$(document).ready(
			function() {
				var date = new Date();
				var d = date.getDate();
				var m = date.getMonth();
				var y = date.getFullYear();

				/*  className colors

				className: default(transparent), important(red), chill(pink), success(green), info(blue)

				 */

				/* initialize the external events
				-----------------------------------------------------------------*/

				$('#external-events div.external-event').each(function() {

					// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
					// it doesn't need to have a start or end
					var eventObject = {
						title : $.trim($(this).text())
					// use the element's text as the event title
					};

					// store the Event Object in the DOM element so we can get to it later
					$(this).data('eventObject', eventObject);

					// make the event draggable using jQuery UI
					$(this).draggable({
						zIndex : 999,
						revert : true, // will cause the event to go back to its
						revertDuration : 0
					//  original position after the drag
					});

				});

				/* initialize the calendar
				-----------------------------------------------------------------*/

				var calendar = $('#calendar').fullCalendar(
						{
							header : {
								left : 'title',
								center : 'agendaDay,agendaWeek,month',
								right : 'prev,next today'
							},
							editable : true,
							firstDay : 1, //  1(Monday) this can be changed to 0(Sunday) for the USA system
							selectable : true,
							defaultView : 'month',

							axisFormat : 'h:mm',
							columnFormat : {
								month : 'ddd', // Mon
								week : 'ddd d', // Mon 7
								day : 'dddd M/d', // Monday 9/7
								agendaDay : 'dddd d'
							},
							titleFormat : {
								month : 'MMMM yyyy', // September 2009
								week : "MMMM yyyy", // September 2009
								day : 'MMMM yyyy' // Tuesday, Sep 8, 2009
							},
							allDaySlot : false,
							selectHelper : true,
							select : function(start, end, allDay) {
								var title = prompt('Event Title:');
								if (title) {
									calendar.fullCalendar('renderEvent', {
										title : title,
										start : start,
										end : end,
										allDay : allDay
									}, true // make the event "stick"
									);
								}
								calendar.fullCalendar('unselect');
							},
							droppable : true, // this allows things to be dropped onto the calendar !!!
							drop : function(date, allDay) { // this function is called when something is dropped

								// retrieve the dropped element's stored Event Object
								var originalEventObject = $(this).data(
										'eventObject');

								// we need to copy it, so that multiple events don't have a reference to the same object
								var copiedEventObject = $.extend({},
										originalEventObject);

								// assign it the date that was reported
								copiedEventObject.start = date;
								copiedEventObject.allDay = allDay;

								// render the event on the calendar
								// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
								$('#calendar').fullCalendar('renderEvent',
										copiedEventObject, true);

								// is the "remove after drop" checkbox checked?
								if ($('#drop-remove').is(':checked')) {
									// if so, remove the element from the "Draggable Events" list
									$(this).remove();
								}

							},

							events : [ {
								title : 'All Day Event',
								start : new Date(y, m, 1)
							}, {
								id : 999,
								title : 'Repeating Event',
								start : new Date(y, m, d - 3, 16, 0),
								allDay : false,
								className : 'info'
							}, {
								id : 999,
								title : 'Repeating Event',
								start : new Date(y, m, d + 4, 16, 0),
								allDay : false,
								className : 'info'
							}, {
								title : 'Meeting',
								start : new Date(y, m, d, 10, 30),
								allDay : false,
								className : 'important'
							}, {
								title : 'Lunch',
								start : new Date(y, m, d, 12, 0),
								end : new Date(y, m, d, 14, 0),
								allDay : false,
								className : 'important'
							}, {
								title : 'Birthday Party',
								start : new Date(y, m, d + 1, 19, 0),
								end : new Date(y, m, d + 1, 22, 30),
								allDay : false,
							}, {
								title : 'Click for Google',
								start : new Date(y, m, 28),
								end : new Date(y, m, 29),
								url : 'http://google.com/',
								className : 'success'
							} ],
						});

			});
</script>

</body>
</html>