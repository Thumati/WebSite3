// Calendar Dates
/* create an array of days which need to be disabled */


/*$http.get('/WebSite/RoomDataBase')
	.success(function(result){
     
	}).error(function($scope){
		 alert(data);
		
	});


var disabledDays = ["11-13-2012","11-14-2012","11-15-2012","11-29-2012","11-30-2012"];

/* utility functions */
/*function nationalDays(date) {
  var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();
  //console.log('Checking (raw): ' + m + '-' + d + '-' + y);
  for (i = 0; i < disabledDays.length; i++) {
    if($.inArray((m+1) + '-' + d + '-' + y,disabledDays) != -1 || new Date() > date) {
      return [false];
    }
  }
  return [true];
}


//Block the Weekends
function noWeekendsOrHolidays(date) {
   var noWeekend = $.datepicker.noWeekends(date);
    if (noWeekend[0]) {
        return nationalDays(date);
    } else {
        return noWeekend;
    }
}




function days() {
            var a = $("#datepicker_start").datepicker('getDate').getTime(),
                b = $("#datepicker_end").datepicker('getDate').getTime(),
                c = 24*60*60*1000,
                diffDays = Math.round(Math.abs((a - b)/(c)));
            
           $("#totaldays").val(diffDays);
}


$(document).ready(function () {
$.datepicker.setDefaults({dateFormat: 'mm/dd/yy',minDate: +1,changeMonth: true,changeYear: true,numberOfMonths: 2,constrainInput:true,beforeShowDay:nationalDays,});
        var selector = function (dateStr) {
            var d1 = $('#datepicker_start').datepicker('getDate');
            var d2 = $('#datepicker_end').datepicker('getDate');
            var diff = 0;
            if (d1 && d2) {
                diff = Math.floor((d2.getTime() - d1.getTime()) / 86400000); // ms per day
            }
         
            $('#totaldays').val(diff);
        }
$('#datepicker_start').datepicker({
            onSelect: function(selectedDate) {
    var minDate = $(this).datepicker('getDate');
    if (minDate) {minDate.setDate(minDate.getDate() + 3);}//min days requires
    $('#datepicker_end').datepicker('option', 'minDate', minDate || 1); // Date + 1 or tomorrow by default
    days();
}});
$('#datepicker_end').datepicker({minDate: 1, onSelect: function(selectedDate) {
    var maxDate = $(this).datepicker('getDate');    
    if (maxDate) {maxDate.setDate(maxDate.getDate() - 1);}
    $('#datepicker_start').datepicker('option', 'maxDate', maxDate); // Date - 1    
    days();
}});


$('#datepicker_start,#datepicker_end').change(selector)
      });

*/




$(document).ready(function () {

            $("#datepicker").datepicker({ minDate: "01/07/2012", maxDate: "01/30/2012" });

            $("#datepicker1").datepicker({ beforeShow: setminDate });

            var start1 = $('#datepicker');      
            function setminDate() {          
                var p = start1.datepicker('getDate');          
                if (p) { 
                    var k ="01/30/2012";            
                    return {
                    minDate: p,
                    maxDate:k
                }};         
            }           
            function clearEndDate(dateText, inst) {          
                end1.val('');      
            }  
            
            
            
            var text1 = 'Single';
            $("#mySelect1 option").filter(function() {
            	
                return this.text == text1; 
                
            }).attr('selected', true);
            alert($("#mySelect1 option:selected").filter().text());
            
            $('button').click(function (){
            	alert("hi");
            	 var a = $("#datepicker").datepicker('getDate').getTime(),
                 b = $("#datepicker1").datepicker('getDate').getTime(),
                 c = 24*60*60*1000,
                 diffDays = Math.round(Math.abs((a - b)/(c)));
             alert(diffDays);
            // alert($('#preview').text(99));
             if(($("#mySelect1 option:selected").text())=="Single")
             {           
            	 
            	 $('div').text(diffDays*99+" *************Room per One Day:99*************  ")
            	          
             }
             else if(($("#mySelect1 option:selected").text())=="Double")
            	 {
            	 
                 $('div').text(diffDays*199+" *************Room per One Day:199*************  ")
            	 }
             else if(($("#mySelect1 option:selected").text())=="Tripple")
        	 {
        	 
             $('div').text(diffDays*179+" *************Room per One Day:179*************  ")
        	 }
             else if(($("#mySelect1 option:selected").text())=="Suite")
        	 {
        	 
             $('div').text(diffDays*199+" *************Room per One Day:199*************  ")
        	 }
             else if(($("#mySelect1 option:selected").text())=="HoneyMoonRoom")
        	 {
        	 
             $('div').text(diffDays*299+" *************Room per One Day:199*************  ")
        	 }
             
             else
            	 {
            	 $('div').text("Hi")
            	 }
            	 
            });
        });
    $(function() {
        $( "#datepicker1" ).datepicker({ dateFormat: 'mm/dd/yyyy' });
        $( "#datepicker" ).datepicker({ dateFormat: 'mm/dd/yyyy' });
    });
   

