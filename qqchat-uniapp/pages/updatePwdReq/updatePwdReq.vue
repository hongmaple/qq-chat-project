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
			<view class="password">
				<input placeholder="请输入原密码" v-model="password" password=true
					placeholder-style="color: rgba(255,255,255,0.8);" />
			</view>
			<view class="password">
				<input placeholder="请输入新密码" v-model="password1" password=true
					placeholder-style="color: rgba(255,255,255,0.8);" />
			</view>
			<view class="password">
				<input placeholder="请再次输入密码" v-model="password2" password=true
					placeholder-style="color: rgba(255,255,255,0.8);" />
			</view>
			<view class="btn" @tap="doLogin">修改</view>
			<view class="res">
				<view @tap="topage">取消</view>
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
				password: null,
				password1: null,
				password2: null
			}
		},
		onLoad() {
			 if (!uni.getStorageSync('token')||typeof(uni.getStorageSync('token')) == "undefined") {
				uni.redirectTo({
					url: '../accountLogin/accountLogin'
				}); //跳转到登录页
			 }
		},
		methods: {
			doLogin() {
				uni.hideKeyboard() //隐藏已经显示的软键盘，如果软键盘没有显示则不做任何操作。
				if (!this.password || typeof(this.password) == "undefined") {
					uni.showToast({
						title: "原密码不能为空",
						icon: "none"
					}); //弹出提示框
					return false;
				}
				if (!this.password1 || typeof(this.password1) == "undefined") {
					uni.showToast({
						title: "新密码不能为空",
						icon: "none"
					}); //弹出提示框
					return false;
				}
				if (!this.password2 || typeof(this.password2) == "undefined") {
					uni.showToast({
						title: "重复密码不能为空",
						icon: "none"
					}); //弹出提示框
					return false;
				}
				if (this.password1 != this.password2) {
					uni.showToast({
						title: "两处密码不一致",
						icon: "none"
					}); //弹出提示框
					return false;
				}
				//示例验证码，实际使用中应为请求服务器比对验证码是否正确。
				uni.showLoading({
					title: '提交中...'
				})
				
				var params = {
					url: "/user/updatePwdReq",
					method: "put",
					needToken: true,
					data: {
						"password": this.password,
						"password1": this.password1,
						"password2": this.password2
					},
					callBack: res => {
						uni.hideLoading()
						if(res.status==200) {
							uni.showToast({
								title: "密码修改成功",
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
	@import "./updatePwdReq.css";
</style>