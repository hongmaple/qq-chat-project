<template>
	<!--pages/user/user.wxml-->
	<view class="container">
		<view class="userinfo" v-if="isAuthInfo" @tap="toUserInfoPage">
			<view class="userinfo-con">
				<view class="userinfo-avatar">
					<!-- <open-data type="userAvatarUrl"></open-data> -->
					<image :src="loginResult.avatarImage?serverUrl+loginResult.avatarImage:'../../static/images/icon/head04.png'"></image>
				</view>
				<view class="userinfo-name">
					<view>{{loginResult.username ? loginResult.username : "用户昵称"}}</view>
					<!-- <open-data type="userNickName"></open-data> -->
				</view>
			</view>
		</view>

		<view class="userinfo-none" v-if="!isAuthInfo">
			<view class="default-pic">
				<image src="../../static/images/icon/head04.png"></image>
			</view>
			<view class="none-login">
				<button class="unlogin" @tap="toLogin">未登录</button>
				<button class="click-login" @tap="toLogin">点击账号登录</button>
			</view>
		</view>
		<!-- end 用户信息 -->

		<view class="list-cont">
			<view class="my-menu">
				<view class="memu-item" @tap="toBindingEmail">
					<view class="i-name">
						<image src="/static/images/icon/promotion.png"></image>
						<text>换绑邮箱</text>
					</view>
					<view class="arrowhead"></view>
				</view>
				<view class="memu-item" @tap="updatePwdReq">
					<view class="i-name">
						<image src="/static/images/icon/toTake.png"></image>
						<text>修改密码</text>
					</view>
					<view class="arrowhead"></view>
				</view>
			</view>
			<!--end 列表项 -->
			
			<view class="log-out" @tap="logout" v-if="isAuthInfo">
				<view class="log-out-n">
					<text>退出登录</text>
				</view>
			</view>
		</view>

	</view>
</template>

<script>
	// pages/user/user.js
	var http = require("../../utils/http.js");
	var util = require("../../utils/util.js");
	var config = require("../../utils/config.js");

	export default {
		data() {
			return {
				orderAmount: 0,
				sts: '',
				collectionCount: 0,
				isAuthInfo: false,
				loginResult: {},
				serverUrl: config.domain
			};
		},
		components: {},
		props: {},

		/**
		 * 生命周期函数--监听页面加载
		 */
		onLoad: function(options) {
			if (!uni.getStorageSync('token')||typeof(uni.getStorageSync('token')) == "undefined") {
				uni.redirectTo({
					url: '../accountLogin/accountLogin'
				}); //跳转到登录页
			}
		},

		/**
		 * 生命周期函数--监听页面初次渲染完成
		 */
		onReady: function() {},

		/**
		 * 生命周期函数--监听页面显示
		 */
		onShow: function() {
			if (!uni.getStorageSync('token')||typeof(uni.getStorageSync('token')) == "undefined") {
				uni.redirectTo({
					url: '../accountLogin/accountLogin'
				}); //跳转到登录页
			}
			//加载当前用户信息
			var ths = this; // var status = ths.data.status
			uni.showLoading();
			var params = {
				url: "/user/info",
				method: "post",
				needToken: true,
				data: {},
				callBack: function(res) {
					uni.hideLoading();
					ths.setData({
						loginResult: res.data
					});
					uni.setStorageSync('loginResult',res.data);
				}
			};
			http.request(params);
			if (ths.loginResult) {
				ths.setData({
					isAuthInfo: true
				})
			} else {
				ths.setData({
					isAuthInfo: false
				})
			}
		},

		/**
		 * 生命周期函数--监听页面隐藏
		 */
		onHide: function() {},

		/**
		 * 生命周期函数--监听页面卸载
		 */
		onUnload: function() {},

		/**
		 * 页面相关事件处理函数--监听用户下拉动作
		 */
		onPullDownRefresh: function() {},

		/**
		 * 页面上拉触底事件的处理函数
		 */
		onReachBottom: function() {},

		/**
		 * 用户点击右上角分享
		 */
		onShareAppMessage: function() {},
		methods: {
			// 跳转绑定手机号
			toBindingEmail: function() {
				uni.navigateTo({
					url: '/pages/binding-email/binding-email'
				});
			},
			updatePwdReq: function() {
				uni.navigateTo({
					url: '/pages/updatePwdReq/updatePwdReq'
				});
			},
			/**
			 * 去登陆
			 */
			toLogin: function() {
				uni.navigateTo({
					url: "../accountLogin/accountLogin"
				})
			},

			/**
			 * 退出登录
			 */
			logout: function() {
				util.removeTabBadge()
				uni.removeStorageSync('loginResult');
				uni.removeStorageSync('token');
				
				// this.$Router.pushTab('/pages/index/index')
				this.setData({
					orderAmount: ''
				});
				uni.navigateTo({
					url: "/pages/accountLogin/accountLogin"
				})
				uni.showToast({
					title: "退出成功",
					icon: "none"
				})
			},
			toUserInfoPage: function(e) {
				uni.navigateTo({
					url: "../userInfo/userInfo"
				})
			},
			punchCard: function(e) {
				var params = {
					url: "/PunchCard",
					method: "post",
					needToken: true,
					data: {},
					callBack: function(res) {
						uni.hideLoading();
						if(res.status==200) {
							uni.showToast({
								title: '打卡成功',
								icon: "success"
							});
						}else {
							uni.showToast({
								title: res.message,
								icon: "none"
							}); //弹出提示框
						}
					}
				};
				http.request(params);
			}
		}
	};
</script>
<style>
	@import "./user.css";
</style>
