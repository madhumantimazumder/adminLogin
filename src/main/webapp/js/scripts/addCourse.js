var specializaions;
var s_id=1;
var st_id=1;
var sc_id=1;
$(document).ready(function() {	
	checkError();
	initializeModal();
	checkUsername();
    getAllCourses();
    getAllSpecializations();
    initializeMinusBtns();
    addSpecializationInput();
    addScheduleInput();
    addCourse();
});
function initializeModal(){
	$('#myModal').on('hidden.bs.modal', function () {
		 location.reload();
	});
}
function checkError(){
	$('#course-name').on('keyup blur', function(event) {
		if($(this).val()==""){
			$(this).parent().find('.error-field.text-error').show();
			
		}
		else{
			$(this).parent().find('.error-field.text-error').hide();
		}
	});
	$('#course-capacity , #course-credit ,#course-code').on('keyup blur', function(event) {
		if( isNaN($(this).val()) || $(this).val()==""){
			$(this).parent().find('.error-field.text-error').show();			
		}
		else{
			$(this).parent().find('.error-field.text-error').hide();
		}
	});
	$(document).on("change",".form-specialization .form-control",function(event){
		$('.error-field.specialization-error').hide();
	});
	$(document).on("change",".form-schedule .form-control",function(event){
		$('.error-field.schedule-error').hide();
	});
}
function checkUsername(){
	var uname = window.localStorage.getItem('username');
	if(uname==null){
		window.location.href = "/adminlogin";
	}
	else{
		$(".user").append(uname);
	}
	$('.logout').click(function(event){
		window.localStorage.removeItem('username');
		window.location.href = "/adminlogin";
	});
}
function isError(specialization_array,schedule_array){
	var flag=false;
	if($('#course-capacity').val()=="" || isNaN($('#course-capacity').val())){
		$('#course-capacity').parent().find('.error-field.text-error').show();
		flag=true;
	}
	if($('#course-code').val()=="" || isNaN($('#course-code').val())){
		$('#course-code').parent().find('.error-field.text-error').show();
		flag=true;
	}
	if($('#course-name').val()==""){
		$('#course-name').parent().find('.error-field.text-error').show();
		flag=true;
	}
	if($('#course-credit').val()=="" || isNaN($('#course-credit').val())){
		$('#course-credit').parent().find('.error-field.text-error').show();
		flag=true;
	}
	
	for(i =0;i<schedule_array.length;i++){
		for(j=i+1;j<schedule_array.length;j++){
			if((schedule_array[i].day==schedule_array[j].day) && (schedule_array[i].time==schedule_array[j].time)){
				$('.error-field.schedule-error').show();
				flag=true;
				break;
			}
		}
		if(flag==true){
			break;
		}
	}
	for(i =0;i<specialization_array.length;i++){
		for(j=i+1;j<specialization_array.length;j++){
			if(specialization_array[i].spId==specialization_array[j].spId){
				$('.error-field.specialization-error').show();
				flag=true;
				break;
			}
		}
		if(flag==true){
			break;
		}
	}
	return flag;
}
function addCourse(){
	$('#add-course').click(function(event){
		event.preventDefault();
		var course_array=[];
		var schedule_array=[];
		var specialization_array=[];
		var day_array=[];
		var time_array=[];
		$(".course-list :checkbox:checked").each(function() {
			course_array.push({'courseId':this.id});
	    });
		$(".form-specialization .form-row select").each(function() {
			specialization_array.push({'spId':$(this).children("option:selected").val()});
	    });
		
		$(".form-schedule .form-row .day select").each(function() {
			day_array.push($(this).children("option:selected").val());
	    });
		$(".form-schedule .form-row .time select").each(function() {
			time_array.push($(this).children("option:selected").val());
	    });
		for(i=0;i<day_array.length;i++){
			var j={'day':day_array[i],'time':time_array[i]};
			schedule_array.push(j);
		}
		
		
		if(isError(specialization_array,schedule_array)==false){
			var json={
					'capacity' : $('#course-capacity').val(),
					'course_code':$('#course-code').val(),
					'name' : $('#course-name').val(),
					'credit': $('#course-credit').val(),
					'preReq':course_array,
					'specializations':specialization_array,
					'schedule':schedule_array
			}
			
			$.ajax({
		        url: "/adminlogin/webapi/api/addCourse",
		        method: "POST",
		        dataType: "JSON",
		        contentType: "application/json",
		        data: JSON.stringify(json),
		        cache: false,
		        async: true,
		        timeout: 600000,
		        success: function(response) {
		        	if(response.status=="200")
		        		$('#myModal').modal('show');
		        	else if(response.status=="202")
		        		$('.course-error').show();
		        },
		        error : function(response){
		        	
		        }
			});
		}
	});
}
function initializeMinusBtns(){
	 $(document).on("click",".fa-minus",function(event) {
		$(this).parent().parent().remove();
	});
}
function addSpecializationInput(){
	
	$('.form-specialization .fa-plus').on('click', function(event){
		event.preventDefault();
	
		s_id=s_id+1;
		var id="sp-"+s_id;
		var dropdown_html = '<div class="form-row"><div class="form-group col-md-12">'+
			 '<div class="pr-0 col-md-12 d-flex flex-row-reverse"><button class="btn fa fa-minus"></button>'+
		  '</div><select id="'+id+'" class="form-control">'+
	      '</select></div></div>';

		
		$('.form-specialization').append(dropdown_html);
		var $elem=$('#'+id);
		addSpecializations($elem);
	});
}
function addScheduleInput(){	

	$('.form-schedule .fa-plus').on('click', function(event){
		event.preventDefault();
		
		sc_id=sc_id+1;
		var id="day-"+sc_id;
		st_id=st_id+1;
		var id1="time-"+s_id;
		var dropdown_html = ' <div class="form-row">'+	
		  	'<div class="col-md-12 d-flex flex-row-reverse">'+
			'<button class="btn fa fa-minus"></button>	</div>	'+  	
	'<div class="form-group col-md-6 day">'+
	  '<select id="'+id+'" class="form-control">'+
	    '<option selected>Monday</option>   <option>Tuesday</option>   <option>Wednesday</option> <option>Thursday</option>'+
	'<option>Friday</option>    <option>Satday</option>  </select></div>	'+	    
	'<div class="form-group  col-md-6 time">'+
	 '<div class="input-group">'+
	      '<select id="'+id1+'" class="form-control">'+
	        '<option selected>9:30</option><option>11:15</option><option>14:00</option><option>15:30</option>'+
	      '</select><div class="input-group-append"><span class="input-group-text">Hour</span>'+
		  '</div> </div></div></div>';
		
		$('.form-schedule').append(dropdown_html);
	});
}
function getAllCourses() {
    $.ajax({
        type: "GET",
        enctype: 'multipart/form-data',
        url: "/adminlogin/webapi/api/getAllCourses",
        processData: false,
        contentType: false,
        cache: false,
        async: true,
        timeout: 600000,

        success: function(result) {
        	addCourses(result);
        },
        error : function(response){
        	
        }
    });
}
function getAllSpecializations() {
    $.ajax({
        type: "GET",
        enctype: 'multipart/form-data',
        url: "/adminlogin/webapi/api/getAllSpecialization",
        processData: false,
        contentType: false,
        cache: false,
        async: true,
        timeout: 600000,

        success: function(result) {
        	specializaions=result;
        	var $elem=$('#sp-1');
        	addSpecializations($elem);
        }
    });
}
function addSpecializations($element){
	var i=0;
	specializaions.forEach(function(special){
		if(i==0){
			var options='<option value="'+special.spId+'" selected="">'+special.name+'</option>';
			i++;
		}
		else{
			var options='<option value="'+special.spId+'">'+special.name+'</option>' ;
		}
		$element.append(options);
	});
}
function addCourses(result) {
    var section = $('.course-list');
    result.forEach(function(course) {
        var checkbox_html = '<div class="custom-control custom-checkbox">' +
            '<input type="checkbox" class="custom-control-input" id="' + course.courseId + '"name="' + course.name + '">' +
            '<label class="custom-control-label" for="'+course.courseId+'">' + course.name + '</label></div>';
        section.append(checkbox_html);
    });
}