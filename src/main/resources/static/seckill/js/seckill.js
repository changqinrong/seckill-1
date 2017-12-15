var seckill={
    URL:{
        now:function () {
            return "/seckill/time/now";
        },
        exposer:function (seckillId) {
            return '/seckill/'+seckillId+'/exposer';

        },
        exe:function (seckillId,md5) {
            return '/seckill/'+seckillId+'/'+md5+'/exeSeckill';

        }
    },
    //校验手机号
    validatePhone:function (phone) {
        if(phone && phone.length==11 && !isNaN(phone)){
            return true;
        }else {
            return false;
        }

    },
    handleSeckill:function (seckillId,node) {
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        var exposerURL=seckill.URL.exposer(seckillId);
        $.get(exposerURL,function (result) {
            if (result && result['code']==0){
                var exportSeckill=result['data'];
                if (exportSeckill['exposer']){
                    var md5=exportSeckill['md5'];
                    //开启秒杀
                    $('#killBtn').one('click',function () {
                        $(this).addClass('disable');//禁用按钮
                        //发送秒杀请求
                        $.post(seckill.URL.exe(seckillId,md5),{},function (result) {
                            if(result){
                                if (result['code']==0){
                                    //秒杀成功
                                    var exeSeckillResult=result['data'];
                                    var state=exeSeckillResult['state'];
                                    var  stateInfo=exeSeckillResult['state_info'];
                                    //显示秒杀结果
                                    node.html('<span class="label label-success">'+stateInfo+'</span>');
                                }else{
                                    node.html('<span class="label label-fail">'+result['msg']+'</span>');
                                }
                            }else {
                                console.log('result='+result);
                            }
                        });

                    });
                    node.show();//显示节点

                }else {
                    seckill.countdown(exposer['seckillId'],exposer['nowTime'],exposer['startTime'],exposer['endTime']);
                }


            }else{
                console.log('result='+result);
            }

        });


    },
    //时间比较
    countdown:function (seckillId,nowTime,startTime,endTime) {
        if (nowTime>endTime){
            //秒杀已经结束
            $('#seckill-box').hide().html('秒杀已经结束').show();
        }else if (nowTime<startTime){
            //秒杀还未开始，开始倒计时
            var killTime=new Date(startTime+1000);
            $('#seckill-box').countdown(killTime,function (event) {
                var format=event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                $('#seckill-box').html(format);
            }).on('finish.countdown',function () {
               seckill.handleSeckill(seckillId,$('#seckill-box'));

            });
        }else{
            //暴露秒杀接口
            seckill.handleSeckill(seckillId,$('#seckill-box'));
        }

    },
    detail:{
        init:function (params) {
            //从cookie中获取killPhone
            var killPhone=$.cookie('killPhone');
            //校验killPhone
            if (!seckill.validatePhone(killPhone)){
                //校验没有通过,绑定手机号码
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show:true,
                    backdrop:false,//禁止位置关闭
                    keyboard:false//关闭键盘时间
                });
                $('#killPhoneBtn').click(function () {
                    //当用户点击提交按钮，获取手机号，校验手机号，将手机号放入cookie中
                    var killPhone=$('#killPhone').val();
                    console.log('killPhone='+killPhone);
                    if (!seckill.validatePhone(killPhone)) {
                        //校验没通过，提示用户
                        console.log("手机号输入不正确");
                        $('#killPhoneMsg').hide().html('<label class="label label-danger">手机号填写错误</label>').show(300);
                    }else{
                        $.cookie('killPhone',killPhone,{expires:7,path:'/seckill'});
                        window.location.reload();//刷新页面
                    }
                });


            }else{
                //已经登陆
                //获取系统时间
                var seckillId=params['seckillId'];
                var startTime=params['startTime'];
                var endTime=params['endTime'];
                console.log(seckillId+";"+startTime+";"+endTime);

                $.get(seckill.URL.now(),function (result) {
                    if(result && result['code']==0){
                        var nowTime=result['data'];
                        seckill.countdown(seckillId,nowTime,startTime,endTime);
                    }else{
                        console.log('result='+result);
                    }


                });
            }

        }
    }

};