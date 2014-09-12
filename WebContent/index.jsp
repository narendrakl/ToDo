<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to ToDo Task</title>
<link href="bootstrap.css" rel="stylesheet" type="text/css">
<link href="main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src='<%=request.getContextPath()%>/jquery.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/bootstrap.js'></script>
<script type="text/javascript">
		$(document).ready(function(){
			
			console.log("document loaded ");
			
			$('a').on('click', function(){
				
				console.log("anchor tag clicked");
				$("#myModal").modal('show');
			});
			$("#add_task").click(function(){
				
				console.log("addtask button clicked");
				var task = $('textarea#description').val();
				if(task == "")
				{
					alert("Empty task cannot be added");
					return false;
				}
				else
				{
					console.log(task);
					$('form').submit();
					
				}
			});
			
		});
		
</script>
</head>
<body>
<div class="nav">
		<div class = "row">
			<div class="col-xs-2"></div>
			<div class="col-xs-8">
			<div class="nav nav-tabs"><h3><a href="#">Click here to add task</a></h3></div>
			
			</div>
			<div class="col-xs-2"></div>
		</div>
</div>
<form action="<%=request.getContextPath()%>/index_int.jsp" method="post">
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Add Tasks Here</h4>
      </div>
      <div class="modal-body">
        <textarea  rows="4" cols="50" id="description" name="task" ></textarea>
      </div>
      	
      <div class="modal-footer">
         <button type="submit" class="btn btn-primary" id="add_task" >Save Task</button>
      </div>
    </div>
  </div>
</div>
</form>

       <%String tasks = (String)request.getAttribute("disp_tasks");
       if(tasks!=null && tasks.contains("/")){
    	   String arr[] = tasks.split("/");
    	   int i=0;
    	   while(i<arr.length){
    	   	if(i>=3 && i%3==1){%>
    	   		<div id="view"></div>
    	   		<%} %>
    	      <div class="col-xs-3">
				<div class="nav">
		          <div class="nav nav-tabs">
		        	<%String a[] = arr[i].split("@"); %>
		        	<p><%out.print(a[0]); %></p>
		           </div>
		        </div>
		      
		         <div>
		               <p><% out.print(a[1]);
		               i++;%></p>
		           </div>
		        </div>
		   <%}
       }%>
 
</body>
</html>