<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form id="payOrder" action="http://127.0.0.1:3020/pay/create_order" method="post">
	<input name="params" value='${params}' />
</form>
</body>

<script
  src="https://code.jquery.com/jquery-2.2.4.js"
  integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI="
  crossorigin="anonymous"></script>
<script type="text/javascript">
	$(function(){
		$("#payOrder").submit();
	});	
</script>
</html>