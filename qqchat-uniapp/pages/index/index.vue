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
		        <block v-for="(item, index) in haoyouList" :key="index">
		          <view class="show-item" @tap="toCustomerServiceChat" :data-touserid='item.id' :data-tousername='item.username' :data-fromavatarimage='item.avatarImage'>
		            <view class="more-prod-pic">
		              <image :src="item.avatarImage?serverUrl + item.avatarImage:'../../static/images/icon/head04.png'" class="more-pic" mode="widthFix"></image>
		            </view>
		            <view class="prod-text-right">
		              <view class="prod-text more">{{item.username}}</view>
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
<script src="../../utils/config.js"></script>
<script>
	var http = require("../../utils/http.js");
	var util = require("../../utils/util.js");
	var config = require("../../utils/config.js");
	export default {
		data() {
			return {
				haoyouList: [],
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
		},
		onShow() {
			var ths = this;
			uni.showLoading();
			var params = {
				url: "/user/haoyoulist",
				method: "post",
				needToken: true,
				data: {
					pageNum: 1,
					pageSize: 1000
				},
				callBack: function(res) {
					uni.hideLoading();
					ths.setData({
						haoyouList: res.data.list
					});
				}
			};
			http.request(params);
		},
		methods: {
			/**
			 * 去聊天
			 */
			toCustomerServiceChat: function (e) {
			      const toUserId = e.currentTarget.dataset.touserid;
			  	  const toUsername = e.currentTarget.dataset.tousername;
				  const toAvatarimage = e.currentTarget.dataset.fromavatarimage;
			        uni.navigateTo({
			          url: '/pages/CustomerServiceChat/CustomerServiceChat?toUserId='+toUserId+'&toUsername='+toUsername+"&toAvatarimage="+toAvatarimage
			  });
			}
		}
	}
</script>

<style>
@import "./index.css";
</style>
