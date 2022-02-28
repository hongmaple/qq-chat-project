var config = require("./config.js"); //统一的网络请求方法
var util = require("./util.js")
import {
	AppType
} from './constant.js'

function request(params, isGetTonken) {
  // 全局变量
  var globalData = getApp().globalData; // 如果正在进行登陆，就将非登陆请求放在队列中等待登陆完毕后进行调用

  // if (!isGetTonken && globalData.isLanding) {
  //   globalData.requestQueue.push(params);
  //   return;
  // }
	
	if (Object.prototype.toString.call(params.data) == '[object Array]') {
		params.data = JSON.stringify(params.data);
	} else if (Object.prototype.toString.call(params.data) == '[object Number]') {
		params.data = params.data + '';
	}
	var needToken = false
	if (params.needToken) {
		if (!uni.getStorageSync('token')||typeof(uni.getStorageSync('token')) == "undefined") {
			return;
		}
		needToken = params.needToken;
	}
  wx.request({
    // url: config.domain + params.url,
	url: (params.domain ? params.domain : config.domain) + params.url,
    //接口请求地址
    data: params.data,
    header: {
      // 'content-type': params.method == "GET" ? 'application/x-www-form-urlencoded' : 'application/json;charset=utf-8',
      // 'Authorization': params.login ? undefined : uni.getStorageSync('token') encodeURIComponent()
			'Authorization': !needToken ? undefined : uni.getStorageSync('token')
    },
    method: params.method == undefined ? "POST" : params.method,
    dataType: 'json',
    responseType: params.responseType == undefined ? 'text' : params.responseType,
    success: function (res) {
		console.log(res.statusCode);
      if (res.statusCode == 200 || res.statusCode == 201 || res.statusCode == 204) {
        //如果有定义了params.callBack，则调用 params.callBack(res.data)
        if (params.callBack) {
          params.callBack(res.data);
        }
      } else if (res.statusCode == 500) {
        uni.showToast({
          title: res.data.message,
          icon: "none"
        });
      } else if (res.statusCode == 401) {
        // 添加到请求队列
        // globalData.requestQueue.push(params); // 是否正在登陆

        // if (!globalData.isLanding) {
        //   globalData.isLanding = true; //重新获取token,再次请求接口

        //   getToken();
        // }
				uni.removeStorageSync('token');
				// #ifdef H5
				const ua = navigator.userAgent.toLowerCase();
				if (ua.search(/MicroMessenger/i) > -1) uni.setStorageSync('appType', AppType.MP)
				// #endif
				uni.hideLoading();
				if (!params.dontTrunLogin) {
					if (uni.getStorageSync('token')) {
						uni.showModal({
							title: "提示",
							content: "登录已过期",
							cancelText: "取消",
							confirmText: "确定",
							success: res => {
								if (res.confirm) {
									// 跳转登录页面
									var url = ''
									// #ifdef H5 || MP-WEIXIN
									if (uni.getStorageSync('appType') == AppType.MP || uni.getStorageSync('appType') == AppType.MINI) {
										url = '/pages/accountLogin/accountLogin'
									} else {
										url = '/pages/accountLogin/accountLogin'
									}
									// #endif
				
									// #ifdef APP-PLUS
									var url = '/pages/accountLogin/accountLogin'
									// #endif
									uni.navigateTo({
										url: url
									})
								} else {
									uni.switchTab({
										url: '/pages/index/index'
									})
								}
							}
						})
					} else {
						uni.showModal({
							title: "提示",
							content: "登录已过期",
							cancelText: "取消",
							confirmText: "确定",
							success: res => {
								if (res.confirm) {
									// 跳转登录页面
									// #ifdef H5	
									uni.navigateTo({
										url: uni.getStorageSync('appType') == AppType.MP ? '/pages/login/login' : '/pages/accountLogin/accountLogin'
									})
									// #endif
													
									// #ifdef MP-WEIXIN
									uni.navigateTo({
										url: '/pages/login/login'
									})
									// #endif
								} else {
									uni.switchTab({
										url: '/pages/index/index'
									})
								}
							}
						})
				// 		// 跳转登录页面
				// 		// #ifdef H5	
				// 		uni.navigateTo({
				// 			url: uni.getStorageSync('appType') == AppType.MP ? '/pages/login/login' : '/pages/accountLogin/accountLogin'
				// 		})
				// 		// #endif
				
				// 		// #ifdef MP-WEIXIN
				// 		uni.navigateTo({
				// 			url: '/pages/login/login'
				// 		})
				// 		// #endif
					}
				}
      } else if (res.statusCode == 400 && !params.errCallBack) {
        uni.hideLoading();
		uni.showToast({
          title: res.data,
          icon: "none"
        });
      } else if (res.statusCode == 405) {
		  uni.hideLoading();
		  uni.showToast({
		    title: "请求方式不支持",
		    icon: "none"
		  });
	  }else {
        //如果有定义了params.errCallBack，则调用 params.errCallBack(res.data)
        if (params.errCallBack) {
					
          params.errCallBack(res);
        }
		uni.hideLoading();
      }

      // if (!globalData.isLanding) {
      //   uni.hideLoading();
      // }
    },
    fail: function (err) {
            uni.hideLoading();
			if (err.errMsg == 'request:fail abort') {
				console.log('请求被取消啦~')
				return
			}
			setTimeout(() => {
				uni.showToast({
					// zheli
					title: "服务器出了点小差",
					icon: "none"
				});
			}, 1);
    }
  });
} //通过code获取token,并保存到缓存


var getToken = function () {
	uni.setStorageSync('token', 'bearer' + result.access_token); //把token存入缓存，请求接口数据时要用
}; // 更新用户头像昵称

/**
 * 微信公众号登录
 * @param {Object} fn  登录成功回调
 * @param {String} code  微信授权返回的code, 用于登录
 */
var mpLogin = function (fn, code) {
	// 发送 res.code 到后台换取 openId, sessionKey, unionId
	request({
		login: true,
		url: '/appLogin',
		data: {
			principal: code,
			appType: AppType.MP, // 登录类型
		},
		callBack: result => {
			loginSuccess(result, fn)
		}
	}, true);

};
/**
 * 登录成功后执行
 * @param {Object} result  登录成功返回的数据
 * @param {Object} fn		登录成功后的回调
 */
function loginSuccess (result, fn) {
	// 保存登陆信息
	wx.setStorageSync('loginResult', result)
	// 保存成功登录标识,token过期判断
	wx.setStorageSync('hadLogin', true)
	wx.setStorageSync('token',result.data); //把token存入缓存，请求接口数据时要用
	if (result.data) {
		wx.setStorageSync('hadBindUser', true);
	} else {
		wx.setStorageSync('hadBindUser', false);
	}
	// var globalData = getApp().globalData;
	// globalData.isLanding = false;
	// while (globalData.requestQueue.length) {
	// 	request(globalData.requestQueue.pop());
	// }

	if (fn) {
		fn()
	}
};

function updateUserInfo() {
  uni.getUserInfo({
    success: res => {
      var userInfo = JSON.parse(res.rawData);
      request({
        url: "/p/user/setUserInfo",
        method: "PUT",
        data: {
          avatarUrl: userInfo.avatarUrl,
          nickName: userInfo.nickName
        }
      });
    }
  });
}

function isUserAuthInfo () {
	// 查看是否授权
	wx.getSetting({
		success (res) {
			if (res.authSetting['scope.userInfo']) {
				// 已经授权，可以直接调用 getUserInfo 获取头像昵称
				wx.getUserInfo({
					success: function (res) {
						console.log(res.userInfo);
					}
				});
			}
		}
	});
}

function mpAuthLogin (page, needCode) {
	// 在微信环境打开,请求公众号网页登陆
	var redirectUrl = null;

	if (!page) {
		redirectUrl = window.location.href
	} else {
		var {
			protocol,
			host,
			pathname,
			hash
		} = window.location
		var redirectUrl = `${protocol}//${host}` + page
	}
	var scope = 'snsapi_userinfo'
	window.location.href = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=' + config.mpAppId +
		'&redirect_uri=' +
		encodeURIComponent(redirectUrl) + '&response_type=code&scope=' + scope + '&state=' + (needCode ? 'needCode' :
			'unNeedCode') +
		'#wechat_redirect'

}

exports.getToken = getToken;
exports.request = request;
exports.updateUserInfo = updateUserInfo;
exports.mpAuthLogin = mpAuthLogin;
exports.loginSuccess = loginSuccess;
exports.mpLogin = mpLogin;
