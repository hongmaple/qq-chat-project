<template>
	<view class="container">
		<!-- 头部搜索区 -->
		  <view class="search-bar">
		    <view class="search-box" @tap="toSearchPage">
		      <image src="/static/images/icon/search.png" class="search-img"></image>
		      <text class="sear-input">搜索</text>
		    </view>
		  </view>
		<!-- 滚动内容区 -->
		  <view class="main">
		    <!-- 右侧内容start -->
		    <scroll-view scroll-y="true" class="rightcontent">
		    <!-- <block wx:for='{{ productList}}' wx:key=''> -->
		      <view class="cont-item">
		        <block v-for="(item, index) in messagesList" :key="index">
		          <view class="show-item" @tap="toCustomerServiceChat" :data-touserid='item.toUserId' :data-tousername='item.toUsername' :data-toavatarimage='item.toAvatarImage'>
		            <view class="more-prod-pic">
						{{item.toAvatarImage}}
		              <image :src="item.toAvatarImage?serverUrl + item.toAvatarImage:'../../static/images/icon/head04.png'" class="more-pic" mode="widthFix"></image>
		            </view>
		            <view class="prod-text-right">
		              <view class="prod-text more">{{item.toUsername}}</view>
		              <view class="cate-prod-info">{{sub(item.contentText,0,20)}}</view>
		              <view class="prod-price more">
		                <text class="big-num">{{dateFormat(item.sendTime)}}</text>
		              </view>
		            </view>
		          </view>
		         
		        </block>
		      </view>
		     <!-- </block> -->
		    </scroll-view>
		    <!-- 右侧内容end -->
		    <!-- </block> -->
		  </view>
	</view>
</template>
<script>
	var http = require("../../utils/http.js");
	var util = require("../../utils/util.js");
	var config = require("../../utils/config.js");
	var websoket = require('../../utils/websoket.js')
	import {
       dateFormat,
	   sub
	} from '../../utils/util.js'
	export default {
		data() {
			return {
				messagesList: [],
				serverUrl: config.domain,
				user: {},
				isAuthInfo: false
			}
		},
		onLoad() {
			var ths = this;
			if (!uni.getStorageSync('token')||typeof(uni.getStorageSync('token')) == "undefined") {
				uni.redirectTo({
					url: '../accountLogin/accountLogin'
				}); //跳转到登录页
				ths.setData({
					isAuthInfo: false
				})
				return;
			}else {
				ths.setData({
					isAuthInfo: true
				})
			}
			ths.loadMessageList();
			var user = uni.getStorageSync("loginResult");
			ths.setData({
				user: user
			});
			var user = uni.getStorageSync('loginResult');
			var socketUrl = config.wsDomain+"/ws/" + user.id;
			this.$store.dispatch('WEBSOCKET_INIT', socketUrl)
			this.$store.state.socketTask.onMessage((res) => {
				console.log("收到服务器内容：" , JSON.parse(res.data));
			    var message = JSON.parse(res.data);
				if(message.status==10400) {
					uni.showToast({
						title: '网络不给力，请检查网络连接',
						icon: "none"
					}); //弹出提示框
				}else if(message.status==13140) {
					ths.loadMessageList();
				}
			});
		},
		onShow() {
			var ths = this;
			ths.loadMessageList();
		},
		methods: {
			/**
			 * 聊天
			 */
			toCustomerServiceChat: function (e) {
			      const toUserId = e.currentTarget.dataset.touserid;
			  	  const toUsername = e.currentTarget.dataset.tousername;
				  const toAvatarimage = e.currentTarget.dataset.toavatarimage;
			        uni.navigateTo({
			          url: '/pages/CustomerServiceChat/CustomerServiceChat?toUserId='+toUserId+'&toUsername='+toUsername+"&toAvatarimage="+toAvatarimage
			  });
			},
			loadMessageList: function() {
				var ths = this;
				var params = {
					url: "/messageList",
					method: "get",
					needToken: true,
					data: {},
					callBack: function(res) {
						console.log(res)
						ths.setData({
						  messagesList: res.data
						});
					}
				};
				http.request(params);
			},
			dateFormat: function(timestamp, format) {
				return dateFormat(timestamp, format);
			},
			sub: function(val, start = 0, end = 10) {
				return sub(val,start,end);
			}
		}
	}
</script>

<style>
@import "./messageList.css";
</style>
