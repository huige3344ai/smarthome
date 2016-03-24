  $(function () {
        //Date range picker with time picker
	$('#reservationtime').daterangepicker({
	        timePicker: true, 
	        timePickerIncrement: 30,
	        format: 'YYYY-MM-DD hh:mm',
			timePicker12Hour : false, //是否使用12小时制来显示时间       
	        showWeekNumbers : true
        });
             
     	
      }); 