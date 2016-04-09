/**
 * 
 */
		$(document).ready(
				function() {
					
					$('#reservationtime').daterangepicker({
						timePicker : true,
						timePickerIncrement : 10,
						format : 'YYYY-MM-DD HH:mm',
						timePicker12Hour : false, //是否使用12小时制来显示时间       
						showWeekNumbers : true,
						startDate : new Date(),
					});
					
					/*添加按钮事件绑定*/
					$("#hy_task_save").click(
							function() {
								var time = $("#reservationtime").val().split(" - ");
								var start = time[0];
								var end = time[1];
								
								if ($.isNotBlank("主题", $("#hy_task_theme"))
										&& $.isNotBlank("内容不能为空",$("#hy_task_contents"))
										&& $.isNotBlank("开始和结束时间",$("#reservationtime"))
										&&$.isErrorDate(start,end)) {

									var taskData = $("#addtaskForm").serialize();

									$.ajax({
										type : "post",
										async : false,
										data : taskData,
										url : "page/taskAction_addTask.action",
										error :  function(){
													 	alert("网络异常,添加失败。");
													 }, 
/*										function(XMLHttpRequest, textStatus,
												errorThrown) {
											alert(XMLHttpRequest.status);
											alert(XMLHttpRequest.readyState);
											alert(textStatus);
										},*/
										success : function(msg) {
											$('#calendar').fullCalendar('refetchEvents');
											alert("添加成功！！！");
											$("#hy_task_theme").val("");
											$("#hy_task_contents").val("");
											$("#reservationtime").val("");
										}
									});
								}
							});
					
					/*更新按钮事件绑定*/					
					$("#hy_task_update").click(
							function() {
								if (confirm("你确定需要修改你的日程?")) {
									var time = $("#update_reservationtime").val().split(" - ");
									var start = time[0];
									var end = time[1];
										if ($.isNotBlank("主题", $("#hy_updatetask_theme"))
												&& $.isNotBlank("内容不能为空",$("#hy_updatetask_contents"))
												&& $.isNotBlank("开始和结束时间",$("#update_reservationtime"))
												&&$.isErrorDate(start,end)) {
											
											var taskData = $("#updatetaskForm").serialize();
											
											$.ajax({
												type : "post",
												async : false,
												data : taskData,
												url : "page/taskAction_updateTask.action",
												error :  function(){
													alert("网络异常,更新失败。");
												}, 
												/*										function(XMLHttpRequest, textStatus,
													errorThrown) {
												alert(XMLHttpRequest.status);
												alert(XMLHttpRequest.readyState);
												alert(textStatus);
											},*/
												success : function(msg) {
													$('#calendar').fullCalendar('refetchEvents');
													alert("更新成功！！！");
												}
											});
										}
										
								}
							});
					
					/*删除按钮事件绑定*/
					$("#hy_task_delete").click(
							function() {
								if (confirm("你确定需要删除你的日程?")) {
									
									var id = $("#hy_task_id").val();
									var taskData={"query.id":id}
									$.ajax({
										type : "post",
										async: false,
										data : taskData,
										url : "page/taskAction_deleteTask.action",
										error :  function(){
											alert("网络异常,删除失败。");
										}, 
										/*										function(XMLHttpRequest, textStatus,
												errorThrown) {
											alert(XMLHttpRequest.status);
											alert(XMLHttpRequest.readyState);
											alert(textStatus);
										},*/
										success : function(msg) {
											if(msg=='1'){
												$('#calendar').fullCalendar('refetchEvents');
												alert("删除成功！！！");
											}else{
												alert("删除失败！！！");
											}
										}
									});
								}
							});
					
					
					
				});

		
		
		$('#calendar').fullCalendar(
						{
							header : {
								left : 'prev,next today',
								center : 'title',
								right : 'month,agendaWeek,agendaDay'
							},
							buttonText : {
								today : '今天',
								month : '月',
								week : '周',
								day : '日'
							},
							titleFormat:{month: ' MMMM--日程安排 '},  
							monthNames : [ "一月", "二月", "三月", "四月", "五月", "六月",
									"七月", "八月", "九月", "十月", "十一月", "十二月" ],
							monthNamesShort : [ "一月", "二月", "三月", "四月", "五月",
									"六月", "七月", "八月", "九月", "十月", "十一月", "十二月" ],
							dayNames : [ "周日", "周一", "周二", "周三", "周四", "周五",
									"周六" ],
							dayNamesShort : [ "周日", "周一", "周二", "周三", "周四",
									"周五", "周六" ],
							today : [ "今天" ],
							firstDay : 1,
							buttonText : {
								today : '今天',
								month : '月',
								week : '周',
								day : '日'
							},
							eventSources : [

							//数据源1
							{
								events : function(start, end, timezone,
										callback) {
									$("#calendar").fullCalendar("removeEvents");//清空数据

									var data = {
										"query.startTime" : start.format(),
										"query.endTime" : end.format()
									};

									$.ajax({
												async : true,
												data : data,
												error : function() {
													alert(error);
												},
												cache : false,
												success : function(doc) {
													eval("var eJson=" + doc);
													var events = [];
													if (eJson != null) {
														$(eJson).each(function(index,element) {
																			var description = element.contents;
																			if(typeof(description) == "undefined"){
																				description=" ";
																			}
																			var title = element.theme+":"+description;
																			events.push({
																						title : title,
																						start : element.startTime,
																						end : element.endTime,
																						id : element.id,
																						backgroundColor:element.color	
																					});
																		})
													}
													callback(events);
												},
												error : function() {
													alert('error')
												},
												type : "POST",
												url : "page/taskAction_findTask.action"
											});
								},
							//color: 'yellow',   // an option!
							//textColor: 'black' // an option!

							} ],
							dayClick : function(date, jsEvent, view) {
								$("#add_task_modal").modal();
								$('#reservationtime').daterangepicker({
									timePicker : true,
									timePickerIncrement : 10,
									format : 'YYYY-MM-DD HH:mm',
									timePicker12Hour : false, //是否使用12小时制来显示时间       
									showWeekNumbers : true,
									startDate : date,
								}	
								
								);

							},
							loading : function(bool) {
								if (bool){
									$('.overlay').show();
									$('.box-header').show();
								}
								else{
									$('.overlay').hide();
									$('.box-header').hide();
								}
							},
							eventClick :function(calEvent, jsEvent, view) {
								
								/* 初始化数据*/
								$('#hy_updatetask_theme').val("");
								$('#hy_updatetask_color').val("");
								$('#hy_updatetask_contents').val("");
								$('#update_reservationtime').val("");
								$('#hy_task_id').val("");
								
								var title = calEvent.title.split(":");
								
								var backgroundColor = calEvent.backgroundColor;
								
								$('#hy_task_id').val(calEvent.id);
								$('#hy_updatetask_theme').val(title[0]);
								$('#hy_updatetask_color').val(backgroundColor);
								$('#hy_updatetask_contents').val(title[1]);
								var startTime = calEvent.start.format().replace("T", " ");
								var endTime = calEvent.end.format().replace("T"," ");
								$('#hy_task_update').css({"background-color": backgroundColor, "border-color": backgroundColor});								
								
								$("#update_task_modal").modal();
								
								$('#update_reservationtime').daterangepicker({
									timePicker : true,
									timePickerIncrement : 30,
									format : 'YYYY-MM-DD HH:mm',
									timePicker12Hour : false, //是否使用12小时制来显示时间       
									showWeekNumbers : true,
									startDate: calEvent.start.format(),
									endDate: calEvent.end.format()
								});
								$('#update_reservationtime').val(startTime+" - "+endTime);

								
							},
						    eventRender: function(event, element) {

						    },
							editable : true,
							eventResize : function(event, delta, revertFunc,
									jsEvent, ui, view) {
								var startTime = event.start.format().replace("T", " ");
								var endTime = event.end.format().replace("T"," ");

								var data = {
									"query.id" : event.id,
									"query.startTime" : startTime,
									"query.endTime" : endTime
								};
								if (!confirm("你确定需要修改你的日程?")) {
									revertFunc(); //恢复原状 
								} else {
									$.post("page/taskAction_updateTask.action",
											data, function(msg) {
												if (msg == '1') {
													alert("修改成功!!!");
												} else {
													alert("后台异常无法修改成功");
													revertFunc(); //恢复原状 	
												}
											});
								}

							},
							//拖动事件 
							eventDrop : function(event, delta, revertFunc,
									jsEvent, ui, view) {

								var startTime = event.start.format().replace("T", " ");
								var endTime = event.end.format().replace("T"," ");

								var data = {
									"query.id" : event.id,
									"query.startTime" : startTime,
									"query.endTime" : endTime
								};
								if (!confirm("你确定需要修改你的日程?")) {
									revertFunc(); //恢复原状 
								} else {
									$.post("page/taskAction_updateTask.action",
											data, function(msg) {
												if (msg == '1') {
													alert("修改成功!!!");
												} else {
													alert("后台异常无法修改成功");
													revertFunc(); //恢复原状 	
												}
											});
										}
						 }	
					});	
					
	 $(function () {
	 
       /* ADDING EVENTS */
        var currColor = "#3c8dbc"; //Red by default
        //Color chooser button
        var colorChooser = $("#color-chooser-btn");
        $("#color-chooser > li > a").click(function (e) {
          e.preventDefault();
          //Save color
          currColor = $(this).css("color");
          //Add color effect to button
          $('#hy_task_color').val(currColor);
          
          $('#hy_task_save').css({"background-color": currColor, "border-color": currColor});
         
          $('#hy_updatetask_color').val(currColor);
          $('#hy_task_update').css({"background-color": currColor, "border-color": currColor});
        });
        
        $('#calendar').fullCalendar('option','aspectRatio', 1.8);
        
        
	 });			