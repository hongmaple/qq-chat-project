<template>
    <view v-if="url">
        <web-view :webview-styles="webviewStyles" :src="url"></web-view>
    </view>
	<view v-else class="error">
		<text class="error-text">打开网页失败，网页不存在或网络问题</text>
	</view>
</template>

<script>
    export default {
        data() {
            return {
                webviewStyles: {
                    progress: {
                        color: '#FF3333'
                    }
                },
				url: null
            }
        },
		 onLoad: function (options) {
			 if (!uni.getStorageSync('token')||typeof(uni.getStorageSync('token')) == "undefined") {
			 	uni.redirectTo({
			 		url: '../accountLogin/accountLogin'
			 	}); //跳转到登录页
			 }
			 if (!options.url || typeof(options.url) == "undefined") {
			 	uni.showToast({
			 		title: "打开网页失败，网页不存在或网络问题",
			 		icon: "none"
			 	}); //弹出提示框
			 	return false;
			 }
			 this.setData({
				 url: options.url
			 })
		 }
		
    }
</script>
<!-- https://uniapp.dcloud.io/component/web-view -->
<style>

</style>