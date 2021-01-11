<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<style type="text/css">
body {
	background-image:
		url('https://images.hdqwalls.com/download/basketball-hd-2560x1440.jpg');
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
	background-position: center;
	font-family: 'Open Sans', sans-serif;
}

.search {
	width: 100%;
	position: relative;
	display: flex;
}

.wrap {
	width: 50%;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}
</style>
<meta charset="UTF-8">
<title>GoogleSearch</title>
</head>
<body>
	<form action='TestProject' method='get'>
		<div class="wrap">
			<div class="search">
				<input type='text' name='keyword' placeholder='keyword'
					style="width: 400px; height: 40px; border-radius: 5px 0 0 5px;" />
				<input type="submit" value='submit'
					style="border-radius: 0 5px 5px 0; width: 60px; height: 40px;font-size:15px;"/>
			</div>
		</div>
	</form>
</body>
</html>



