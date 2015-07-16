<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="exTwitter.OnceBean"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>

<html>
	<% Calendar cal = Calendar.getInstance();
		int now_minute = cal.get(Calendar.MINUTE);
		cal.add(Calendar.MINUTE,+5-(now_minute%5));
		int now_year = cal.get(Calendar.YEAR);
  		int now_month = cal.get(Calendar.MONTH)+1;
  		int now_day = cal.get(Calendar.DATE);
  		int now_hour = cal.get(Calendar.HOUR_OF_DAY);
  		now_minute = cal.get(Calendar.MINUTE);
  		
  		Integer cont = (Integer)session.getAttribute("contribution");
  		session.setAttribute("contribution",0);
  		int contInt;
  		if(cont == null){
  			contInt = 0;
  		}
  		else{
  			contInt = cont.intValue();
  		}
	%>
	
	<link rel="stylesheet" type="text/css" href="style.css">
	
	<head>
		<title>単発ツイート作成</title>
	</head>

	<body>

		<jsp:include page="header.jsp" />
		
	<!-- タイトル -->
		<div id="a">
			<div id="label">
				単発ツイート作成
			</div>
			
			<!-- 以下、フォームの配置 -->
			<form method="post" action="OnceUpdate" onSubmit="return twbtn()">
				<center>
					
				<!-- ツイートのタイトルと本文の入力フォーム -->
					<textarea name="text" style="width:400px;height:200px;resize:none;font-size:22" maxlength="140" placeholder="ツイートを入力してね"></textarea><br>
				<!-- ツイート終わり -->
				<!-- 日付指定チェックボックス -->
					<dev id="check">
						<br>
						<input type="checkbox" id="chk1" name="chk1" onclick='chkdisp(this)' /><label for="chk1">日付指定する</label>
						<br>
					</dev>
				<!--ツイート日時の入力フォーム-->
					<div id="select_t">
						<br>
						日付指定
						<% out.println("<input type=\"number\" style=\"width:70px;height:30;font-size:20\" name=\"year\" require max=2020 min=2015 value="+ now_year +" step=1>年</input>"); %>
						<% out.println("<input type=\"number\" style=\"width:50px;height:30;font-size:20\" name=\"month\" require max=12 min=1 value="+ now_month +" step=1>月</input>"); %>
						<% out.println("<input type=\"number\" style=\"width:50px;height:30;font-size:20\" name=\"day\" require max=31 min=1 value="+ now_day +" step=1>日</input>"); %>
						<br><br>
						時刻指定
						<% out.println("<input type=\"number\" style=\"width:50px;height:30;font-size:20\" name=\"hour\" require max=23 min=0 value="+ now_hour +" step=1>時</input>"); %>
						<% out.println("<input type=\"number\" style=\"width:50px;height:30;font-size:20\" name=\"minute\" require max=59 min=0 value="+ now_minute +" step=5>分</input>"); %>
					</div>
					<!--日時ここまで-->
				<br>
				<dev id="botton">
					<input type="submit" style="width:100px;height:40px;font-size:20" value="ツイート">
				</dev>
				</form>
		</div>
	
	<!-- 以下、javascript -->
	<script type="text/javascript">
	
	 	function chkdisp(obj) {
 			if( obj.checked ){
 		 		document.getElementById('select_t').style.display = "block";
  			}
  			else {
  				document.getElementById('select_t').style.display = "none";
			}
		}
		
		function disp(){
			<% out.print( "var contInt = " + contInt + ";" ); %>
			document.getElementById('select_t').style.display = "none";
			var checkbox = document.getElementById("chk1");
			checkbox.checked = false;
			if(contInt == 1)	window.confirm('ツイート登録完了');
			else if(contInt == -1) window.confirm('ツイート登録失敗');
		}
		
		function twbtn(){
			if(window.confirm('ツイート登録しますか？')){
				return true;
			}
			else{
				window.alert('キャンセルしました');
				return false;
			}
		}
		
		window.onload = disp;

	</script>
	<!-- ここまでjavascript -->

	</body>
</html>