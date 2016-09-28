//存放交互逻辑js代码
//js模块化
//seckill.detail.init(params);
var seckill={
		//封装秒杀相关ajax地址
		URL:{
			now:function(){
				return '/SecondKill/seckill/time/now';
			},
			
			exposer:function(seckillId){
				return '/SecondKill/seckill/'+seckillId+'/exposer';
			},
			execution:function(seckillId,md5){
				return '/SecondKill/seckill/'+seckillId+'/'+md5+'/execution';
			}
		},
		
		handleSecKill: function(seckillId,node){
			//处理秒杀逻辑
			node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
			$.post(seckill.URL.exposer(seckillId),{},function(result){
				//回调函数中执行交互流程
				if(result && result['success']){
					var exposer = result['data'];
					if(exposer['exposed']){
						//开启秒杀
						//获取地址
						var md5 = exposer['md5'];
						var killUrl = seckill.URL.execution(seckillId,md5);
						console.log("killurl"+killUrl);
						console.log("nowurl"+seckill.URL.exposer());
						console.log($('#killBtn'));
						$('#killBtn').one('click',function(){
							//绑定执行秒杀请求的操作
							$(this).addClass('disabled');
							//发送秒杀请求
							$.post(killUrl,{},function(result){
								console.log("postkillurl");
								if(result && result['success']){
									var killResult = result['data'];
									var state = killResult['state'];
									var stateInfo = killResult['stateInfo'];
									//显示秒杀结果
									node.html('<span class="label label-success">'+stateInfo+'</span>');
								}
							});
							
						});
						node.show();
					}else{
						//未开启秒杀
						var now = exposer['now'];
						var start = exposer['start'];
						var end = exposer['end'];
						//重新计算逻辑
						seckill.countdown(SeckillId,now,start,end);
						
					}
				}else{
					console.log("result: "+result);
				}
				
			});
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
				
				seckillBox.countdown(startTime,function(event){
					var format = event.strftime('秒杀倒计时： %D天 %H时 %M分 %S秒');
					seckillBox.html(format);
				}).on('finish countdown',function(){//时间完成后回调事件
					//获取秒杀地址,控制实现逻辑，执行操作
					seckill.handleSecKill(seckillId,seckillBox);
				});
				
			}else{
				//秒杀开始
				seckill.handleSecKill(seckillId,seckillBox);
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
						seckill.countDown(seckillId,nowTime,startTime,endTime);
					}else{
						console.log('result: '+result);
					}
				});
			}
		}
		
}












