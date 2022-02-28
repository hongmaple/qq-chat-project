<template>
	<view>
		<!-- 登录页面正中间图标 -->
		<view class="logo">
			<view class="img">
				<image mode="widthFix"
					src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic%2Fd0%2Fa5%2F86%2Fd0a586e0cece1f1dec648e9aa3d8c9d6.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633761077&t=84f5ce7dc4918ef2e89d1fa4e1e72504">
				</image>
			</view>
		</view>
		<!-- 注册界面 -->
		<view class="form userform">
			<view class="username">
				<input placeholder="请输入邮箱" v-model="username" placeholder-style="color: rgba(255,255,255,0.8);" />
			</view>
			<view class="password">
				<input placeholder="请输入密码" v-model="password" password=true
					placeholder-style="color: rgba(255,255,255,0.8);" />
			</view>
			<view class="btn" @tap="doLogin">立即登录</view>
			<view class="res">
				<view @tap="toRegister">立即注册</view> 
				<view @tap="toRetrievePassword" style="margin-left: 20px;">找回密码</view>
			</view>
		</view>
 
	</view>
</template>
 
<script>
	var http = require("../../utils/http");
	var util = require('../../utils/util.js');
	export default {
		data() {
			return {
				username: "",
				verificationCode: '',
				password: null,
				getCodeText: '获取验证码',
				getCodeBtnColor: "#ffffff",
				getCodeisWaiting: false
			}
		},
		onLoad() {
 
		},
		methods: {
			Timer() {},
			getCode() {
				uni.hideKeyboard() //隐藏已经显示的软键盘，如果软键盘没有显示则不做任何操作。
				if (this.getCodeisWaiting) {
					return;
				}
				// if (!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(this.phoneNumber))) { //校验手机号码是否有误
				// 	uni.showToast({
				// 		title: '请填写正确手机号码',
				// 		icon: "none"
				// 	});
				// 	return false;
				// }
				if (!(/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(this.username))) { //校验手机号码是否有误
					uni.showToast({
						title: '请填写正确邮箱',
						icon: "none"
					});
					return false;
				}
				this.getCodeText = "发送中..." //发送验证码
				this.getCodeisWaiting = true;
				this.getCodeBtnColor = "rgba(255,255,255,0.5)" //追加样式，修改颜色
				var ths = this;
				//示例用定时器模拟请求效果
				//setTimeout(()用于在指定的毫秒数后调用函数或计算表达式
				var params = {
					url: "/user/sendEmailCode?email="+ths.username,
					method: "post",
					contentType: 'application/x-www-form-urlencoded',
					callBack: res => {
						if(res.status==200) {
							uni.showToast({
								title: '验证码已发送',
								icon: "none"
							}); //弹出提示框
						}else {
							uni.showToast({
								title: '验证码发送失败',
								icon: "none"
							}); //弹出提示框
						}
						this.setTimer(); //调用定时器方法
					},
				}
				http.request(params)
			},
			//setTimer： 需要每隔一段时间执行一件事的的时候就需要使用SetTimer函数
			setTimer() {
				let holdTime = 60; //定义变量并赋值
				this.getCodeText = "重新获取(60)"
				//setInterval（）是一个实现定时调用的函数，可按照指定的周期（以毫秒计）来调用函数或计算表达式。
				//setInterval方法会不停地调用函数，直到 clearInterval被调用或窗口被关闭。
				this.Timer = setInterval(() => {
					if (holdTime <= 0) {
						this.getCodeisWaiting = false;
						this.getCodeBtnColor = "#ffffff";
						this.getCodeText = "获取验证码"
						clearInterval(this.Timer); //清除该函数
						return; //返回前面
					}
					this.getCodeText = "重新获取(" + holdTime + ")"
					holdTime--;
				}, 1000)
			},
			doLogin() {
				var ths = this;
				uni.removeStorageSync('loginResult');
				uni.removeStorageSync('token');
				uni.hideKeyboard() //隐藏已经显示的软键盘，如果软键盘没有显示则不做任何操作。
				//模板示例部分验证规则
				if (!(/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(this.username))) { //校验邮箱
					uni.showToast({
						title: '请填写正确邮箱',
						icon: "none"
					});
					return false;
				}
				if (!this.password || typeof(this.password) == "undefined") {
					uni.showToast({
						title: "密码不能为空",
						icon: "none"
					}); //弹出提示框
					return false;
				}
				//示例验证码，实际使用中应为请求服务器比对验证码是否正确。
				uni.showLoading({
					title: '提交中...'
				})
				
				var params = {
					url: "/user/login",
					method: "post",
					data: {
						"username": this.username,
						"password": this.password
					},
					callBack: res => {
						uni.hideLoading()
						if(res.status==200) {
							uni.setStorageSync('token',res.data);
							var params = {
								url: "/user/info",
								method: "post",
								needToken: true,
								data: {},
								callBack: function(res) {
									ths.setData({
										loginResult: res.data
									});
									uni.setStorageSync('loginResult',res.data);
								}
							};
							http.request(params);
							http.loginSuccess(res, () => {
								uni.showToast({
									title: "登录成功",
									icon: 'none',
									complete: () => {
										// this.$Router.pushTab('/pages/index/index')
										setTimeout(() => {
											wx.switchTab({
											url: '/pages/user/user'
										});
										},1000)
										
									}
								})
							})
						}else {
							uni.showToast({
								title: res.message,
								icon: "none"
							}); //弹出提示框
						}
					},
				}
				http.request(params)
			},
			toRegister() {
				uni.hideKeyboard() //隐藏已经显示的软键盘，如果软键盘没有显示则不做任何操作。
				uni.navigateTo({
					url: '../register/register'
				}); //跳转到注册
				//uni.navigateBack(); //关闭当前页面，返回上一页面
			},
			toRetrievePassword() {
				uni.hideKeyboard() //隐藏已经显示的软键盘，如果软键盘没有显示则不做任何操作。
				uni.navigateTo({
					url: '../retrievePassword/retrievePassword'
				}); //跳转到找回密码
				//uni.navigateBack(); //关闭当前页面，返回上一页面
			},
			topage: function (){
			    uni.navigateBack({
			        delta: 1
			    });
			},
		}
	}
</script>
 
<style lang="scss">
	
 
	page {
		background: #3F536E; //整一个页面的背景颜色
		// linear-gradient(to bottom, #f06c7a 0%, #f06c7a 100%);
		height: 100%;
	}
 
	.icon {
		color: #ffffff;
	}
 
	.logo {
		width: 100%;
		height: 45vw;
		display: flex;
		justify-content: center;
		align-items: center;
 
		.img {
			width: 25%;
			height: 25vw;
 
			image {
				width: 100%;
				border-radius: 100%;
			}
		}
	}
 
	.form {
		width: 86%;
		padding: 0 7%;
		font-size: 30upx;
 
		.username,
		.password,
		.code {
			width: calc(100% - 90upx);
			height: 90upx;
			display: flex;
			align-items: center;
			border-radius: 45upx;
			background-color: rgba($color: #ffffff, $alpha: 0.1);
			padding: 0 45upx;
			margin-bottom: 26upx;
 
			input {
				width: 100%;
				height: 50upx;
				color: rgba($color: #ffffff, $alpha: 0.8);
				font-weight: 200;
			}
		}
 
		.btn {
			color: #f06c7a;
			width: 100%;
			height: 90upx;
			display: flex;
			justify-content: center;
			align-items: center;
			border-radius: 45upx;
			background-color: #fff;
			font-size: 40upx;
		}
	}
 
	.userform {
		.username {
			position: relative;
 
			.get-code {
				position: absolute;
				height: 90upx;
				display: flex;
				align-items: center;
				justify-content: center;
				right: 0;
				padding: 0 40upx;
				z-index: 3;
 
				&:after { //点击以后，左边出现白色的线
					content: " ";
					width: 1upx; //宽度为1upx
					height: 50upx; //高度为50upx
					background-color: #fff;  //背景颜色为白色
					position: absolute;
					z-index: 3;
					margin-right: 100%;
					left: 0;
					top: 20upx;
				}
			}
		}
 
		.res {
			display: flex;
			justify-content: center;
			align-items: center;
			height: 100upx;
			color: rgba($color: #ffffff, $alpha: 0.8);
		}
	}
	@import "./accountLogin.css";
</style>