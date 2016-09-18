<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>秒杀详情页</title>

<!-- Bootstrap -->


<link href="../css/bootstrap.min.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    
</head>
<body>
	<div class=container"">
		<div class="panel panel-default text-center">
			<div class="panel-heading"><h1>${seckill.name}</h1></div>
		
		<div class="panel-body">
			<h2 class="text-danger">
				<span class="glyphicon glyphicon-time"></span>
				<span class="glyphicon" id="seckill-box"></span>
			</h2>
		</div>
	</div>
</div>

<div id="killPhoneModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3 class="modal-title text-center">
                            <span class="glyphicon glyphicon-phone"></span>秒杀电话：
                        </h3>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-xs-8 col-xs-offset-2">
                                <input type="text" name="killphone" id="killphoneKey" placeholder="填手机号^O^" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <span id="killphoneMessage" class="glyphicon"></span>
                        <button type="button" id="killPhoneBtn" class="btn btn-success">
                            <span class="glyphicon glyphicon-phone"></span>
                            Submit
                        </button>
                    </div>
                </div>
            </div>
        </div>

</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="https://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.js"></script>

<!-- 交互逻辑 -->
<script src="/resource/script/seckill.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		
		//使用EL表达式传入参数 
		seckill.detail.init({
			seckillId:"${seckill.seckillId}",
			startTime:"${seckill.startTime.time}",
			endTime:"${seckill.endTime.time}"
			
		});
	});
	
</script>

</html>