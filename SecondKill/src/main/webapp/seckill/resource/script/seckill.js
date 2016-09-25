//存放交互逻辑js代码
//js模块化
//seckill.detail.init(params);
var seckill={
		//封装秒杀相关ajax地址
		URL:{
			
		},
		//验证手机号
		validatePhone: function(phone){
			if(phone && phone.length==11 &&!isNaN(phone)){
				return true;
			}else{
				return false;
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
			}
		}
		
}













