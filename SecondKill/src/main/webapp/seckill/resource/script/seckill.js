//存放交互逻辑js代码
//js模块化
//seckill.detail.init(params);
var seckill={
		//封装秒杀相关ajax地址
		URL:{
			now:function(){
				return '/seckill/time/now';
			}
		},
		//验证手机号
		validatePhone: function(phone){
			if(phone && phone.length==11 &&!isNaN(phone)){
				return true;
			}else{
				return false;
			}
		},
		
		countDown:function(seckillId,nowTime,startTime,endTime){
			var seckillBox = $('#seckill-box');
			//时间判断
			if(nowTime>endTime){
				seckillBox.html('秒杀结束!');
			}else if(nowTime<startTime){
				//没有开始
				var killTime = new Date(startTime+1000);
				seckillBox.countdown(killTime,function(event){
					var format = event.strftime('秒杀倒计时： %D天 %H时 %M分 %S秒');
					seckillBox.html(format);
				});
				
			}else{
				//秒杀开始
				
			}
		},
		
		//详情页秒杀逻辑
		detail:{
			//详情页初始化
			init:function(params){
				//手机验证和登录，计时交互
				//规划交互流程
				//在cookie中查找手机号
				var killPhone = $.cookie('killPhone');
				var startTime = params['startTime'];
				var endTime = params['endTime'];
				var seckillId = params['seckillId'];
				
				if(!seckill.validatePhone(killPhone)){
					//绑定手机号
					//控制输出
					//TODO
					console.log("panduan"+!seckill.validatePhone(killPhone));
					console.log("killPhone: "+killPhone);
					var killPhoneModal = $('#killPhoneModal');
					
					killPhoneModal.modal({
						show:true,//显示弹出层
						backdrop:'static',
						keyboard:false
						
					});
					$('#killPhoneBtn').click(function(){
						var inputPhone = $('#killPhoneKey').val();
						if(seckill.validatePhone(inputPhone)){
							$.cookie('killPhone',inputPhone,{expire:7,path:'/SecondKill'});
							window.location.reload();
						}else{
							$('#killPhoneMessage').hide().html('<lable class="lable lable-danger">手机号错误！</lable>').show(300);
						}
					});
				}
				
				//计时交互
				$.get(seckill.URL.now(),{},function(result){
					if(result && result['success']){
						var nowTime = result['data'];
						//时间判断
						seckill.countdown(seckillId,nowTime,startTime,endTime);
					}else{
						console.log('result: '+result);
					}
				});
			}
		}
		
}













