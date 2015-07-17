<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="exTwitter.RoutineBean"%>
<%@page import="java.util.ArrayList"%>


<%
/*定期ツイート一覧受け取り*/
ArrayList<RoutineBean> tweetList = (ArrayList<RoutineBean>)session.getAttribute("tweetList");
%>


<html>
	
	<link rel="stylesheet" type="text/css" href="style.css">
	
	<head>
		<title>定期ツイート作成</title>
		
		<!--各入力フォームのスタイル記述-->
		<style type="text/css">
		
			/*ツイートのタイトルの設定*/
			.twt_title { width:200px;
				   height:30px;
				   font-size:20;
				 }
						
			/*ツイートの本文の設定*/
			.twt_text { width:400px;
					    height:200px;
					    resize:none;
					    font-size:22;
					  }
		
			/*４桁の数字を入力する部分用*/
			.inp_4num { width:70px;
					   height:30px;
					   font-size:20;}
			/*２桁の数字を入力する部分用*/ 
			.inp_2num { width:50px;
					    height:30px;
					    font-size:20;
				      }
				      
			/*ラジオボタン＆チェックボックス*/
			.rad_chk { width:20px;
					   height:20px;
					 }
					 
			/*ツイート一覧の設定*/
			.tbl { font-size:22;
				 }
				 
			/*ボタンの設定*/
			.btn { width:130px;
				   height:40px;
				   font-size:20;
				 }
			
		</style>
		<!--スタイル終わり-->
	</head>

	<body>
	
		<jsp:include page="header.jsp" />
		
		<!-- タイトル -->
		<div id="a">
			<div id="label">
				定期ツイート作成
			</div>
			
			<!-- 以下、フォームの配置 -->
			<!--<form action="test">-->
			<form action="" onsubmit="return tweet()">
				<center>
					
				<!-- ツイートのタイトルと本文の入力フォーム -->
					<input type="text" class="twt_title" size="7" id="title" maxlength="10" placeholder="ツイートのタイトル">
						10文字まで<br><br>
					<textarea id="text" class="twt_text" maxlength="140" placeholder="ツイートを入力してね"></textarea><br>
				<!-- ツイート終わり -->
					
					<br>
					
				<!--ツイートの期間と時刻の入力フォーム-->
					<div id="inputbox">
						期間　
						<input type="number" class="inp_4num" id="start_year" value=2015 require autofocus max=2020 min=2015 step=1 >年</input>
						<input type="number" class="inp_2num" id="start_month" value=1 require autofocus max=12 min=1 step=1 >月</input>
						<input type="number" class="inp_2num" id="start_day" value=1 require autofocus max=31 min=1 step=1 >日</input>
						 ～ <input type="number" class="inp_4num" id="end_year" value=2015 require autofocus max=2020 min=2015 step=1 >年</input>
						<input type="number" class="inp_2num" id="end_month" value=1 require autofocus max=12 min=1 step=1 >月</input>
						<input type="number" class="inp_2num" id="end_day" value=1 require autofocus max=31 min=1 step=1 >日</input>
					
						<br><br>
						ツイートする時刻　　
						<input type="number" class="inp_2num" id="tweet_hour" value=0 require autofocus max=23 min=0 step=1 >時</input>
						<input type="number" class="inp_2num" id="tweet_minute" value=0 require autofocus max=55 min=0 step=5 >分</input>
					</div>
				<!--期間と時刻終わり-->
					
					<br><br>
					
				<!--ツイート周期の入力フォーム-->
					ツイート周期　
					<!-- 曜日or日付 -->
					<input type="radio" class="rad_chk" id="weekday" name="entryPlan" value="hoge1" onclick="entryChange();" checked="checked" /><label for="weekday">曜日指定</label>　
					<input type="radio" class="rad_chk" id="day" name="entryPlan" value="hoge2" onclick="entryChange();" /><label for="day">日付指定</label>
					
					
					<!--曜日指定フォームの表示-->
					<div id="test1" name="weekly">
						<br>
						<input type="checkbox" class="rad_chk" name="月" id="chk1"/><label for="chk1">月 </label>
						<input type="checkbox" class="rad_chk" name="火" id="chk2"/><label for="chk2">火 </label>
						<input type="checkbox" class="rad_chk" name="水" id="chk3"/><label for="chk3">水 </label>
						<input type="checkbox" class="rad_chk" name="木" id="chk4"/><label for="chk4">木 </label>
						<input type="checkbox" class="rad_chk" name="金" id="chk5"/><label for="chk5">金 </label>
						<input type="checkbox" class="rad_chk" name="土" id="chk6"/><label for="chk6">土 </label>
						<input type="checkbox" class="rad_chk" name="日" id="chk7"/><label for="chk7">日 </label>
						<input type="checkbox" class="rad_chk" id="chk8" onclick="chkWeek();" /><label for="chk8">平日</label>
					</div>

					<!--日付指定フォームの表示-->
					<div id="test2">
						<br>
						<table style="font-size:25">
							<tr>
								<td>
								<td><input type="number" class="inp_2num" name="days" id="num0" value=1  max=31 min=1 step=1 >日</input>
								<td><input type="checkbox" class="rad_chk" id="monthend"><label for="monthend">月末</label>
						</table>
						<input type="button" value="追加入力" onclick="addElement()"> 
					</div>

					<br><br>
					<input type="submit"  class="btn" value="ツイートする">
					
					
				</center>
				
			</form>
			<!--</form>-->
			<!-- ここまでフォームの配置 -->
			
			<br>
			
			<!--以下、定期ツイート一覧の表示-->
			登録されている定期ツイート一覧
			<div align="right"><font size="5">
				<a href="">定期ツイート削除画面へ</a>
			</font><div>
			
			<center>
				<!--テーブル-->
				<table border="2" class="tbl">
					<tr>
						<th>　ツイートタイトル　</th>
						<th>　ツイート時刻　</th>
						<th>　開始日　</th>
						<th>　終了日　</th>
					</tr>
					
					<%
						for(int i=0;i<tweetList.size();i++){
							out.print("<tr>");
							out.print("<td>" + tweetList.get(i).getTitle() + "</td>");
							out.print("<td>" + tweetList.get(i).getPost_time() + "</td>");
							out.print("<td>" + tweetList.get(i).getStart_date() + "</td>");
							out.print("<td>" + tweetList.get(i).getEnd_date() + "</td>");
							out.print("</tr>");
						}
					%>
				
				</table><!--テーブル終わり-->
			</center>
			<!--定期ツイート一覧表示終わり-->
			
		</div><!--<div id="a">終わり-->
	
	<!-- 以下、javascript -->
	<script type="text/javascript">
	
		//<!-- ウィンドウがロードされたときに処理する関数指定 -->
		window.onload = entryChange;
		//window.onload = doneDialog(flg):
		
		
		//<!-- 日付を指定する入力フォームを追加する関数 -->
		var i = 1; // <!-- i-1 = 入力フォームを追加した回数 -->
		function addElement() { 
			if(i<=31){
				Tr  = document.createElement("Tr");
				Td1  = document.createElement("Td");
				Td2  = document.createElement("Td");	
				Td3  = document.createElement("Td");
				Td1.innerHTML="<button onclick='removeElement("+i+")'>削除</button> "
				Td2.innerHTML="<input type=\"number\" class=\"inp_2num\" name=\"days\" id=\"num"+i+"\""+"value=1  max=31 min=1 step=1 >日</input>";
				Tr.appendChild(Td1);
				Tr.appendChild(Td2);
				Tr.appendChild(Td3); 
				Tr.id = i;
				var objTBL = document.getElementsByTagName("tbody").item(1); 
				objTBL.appendChild(Tr);
				i = i+1;
			}
		} 
	
		//<!--追加した要素を削除する関数-->
		function removeElement(id) { 
			var element = document.getElementById(id);
			var tbl = document.getElementsByTagName("tbody").item(1);
			tbl.removeChild(element); 	
		} 
		
		//<!--「曜日指定」と「日付指定」で表示切替する関数-->
		function entryChange(){
			radio = document.getElementsByName('entryPlan');
			if(radio[0].checked) {
				document.getElementById('test1').style.display = "";
				document.getElementById('test2').style.display = "none";
			}else if(radio[1].checked) {
				document.getElementById('test1').style.display = "none";
				document.getElementById('test2').style.display = "";
			}
		}
		
		
		//<!--「ツイートする」ボタン押下で確認ダイアログ表示する関数-->
		function tweet(){
			var days=[];
			radio = document.getElementsByName('entryPlan');
			
			//<!--ツイート周期の文字列を生成する。-->
			//<!--ラジオボタンによる表示切替に合わせてダイアログに表示する文字列を切り替える。-->
			if(radio[0].checked){
				
				//<!--指定曜日の受け取り-->
				var strDays="指定した曜日\n";
				for(var j=1;j<=7;j++){
					if(document.getElementById("chk"+j).checked){
						days[days.length]=document.getElementById("chk"+j).name;
					}
				}
			
			}else{	
				
				//<!--指定日付の受け取り-->
				var strDays="指定した日付\n";
				for(var j=0;j<i;j++){
					if(document.getElementById("num"+j)) {
						days[days.length]=document.getElementById("num"+j).value;
					}
				}
				if(document.getElementById("monthend").checked){
					days[days.length]="月末"
				}
			}
			//<!--データから文字列生成-->
			for(var j=0;j<days.length;j++){
				//<!--文字の間にコンマを挟むための処理-->
				if(j!=0){
					strDays=strDays+",";
				}
				strDays=strDays+days[j];
			}
			//<!--ツイート周期の文字列の生成終わり-->
			
			
			//<!--ダイアログに表示する文字列の生成-->
			var strDialog="ツイートの内容は以下でよろしいですか？\n\n\n"+
					"タイトル\n"+document.getElementById("title").value+"\n\n"+
					"本文\n"+document.getElementById("text").value+"\n\n"+
					"開始日\n"+document.getElementById("start_year").value+"年"+document.getElementById("start_month").value+"月"+document.getElementById("start_day").value+"日\n\n"+
					"終了日\n"+document.getElementById("end_year").value+"年"+document.getElementById("end_month").value+"月"+document.getElementById("end_day").value+"日\n\n"+
					"時刻\n"+document.getElementById("tweet_hour").value+"時"+document.getElementById("tweet_hour").value+"分\n\n"+
					strDays;
					
					return confirm(strDialog);
			}
		
		//<!--「平日」にチェックが入った時の処理-->
		function chkWeek(){
			wd = document.getElementById("chk8");
			if(wd.checked){
				for(var j=1;j<=5;j++){
					document.getElementById("chk"+j).checked=true;
				}
			}else{
				for(var j=1;j<=5;j++){
					document.getElementById("chk"+j).checked=false;
				}
			}
			
		}
		
		//<!--登録完了ダイアログ-->
		function doneDialog(flg){
			alert("登録完了しました！");
		}
		
	</script>
	<!-- ここまでjavascript -->
	
	</body>
</html>