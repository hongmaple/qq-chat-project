(function(e){function n(n){for(var o,r,s=n[0],c=n[1],g=n[2],u=0,l=[];u<s.length;u++)r=s[u],Object.prototype.hasOwnProperty.call(t,r)&&t[r]&&l.push(t[r][0]),t[r]=0;for(o in c)Object.prototype.hasOwnProperty.call(c,o)&&(e[o]=c[o]);d&&d(n);while(l.length)l.shift()();return i.push.apply(i,g||[]),a()}function a(){for(var e,n=0;n<i.length;n++){for(var a=i[n],o=!0,r=1;r<a.length;r++){var c=a[r];0!==t[c]&&(o=!1)}o&&(i.splice(n--,1),e=s(s.s=a[0]))}return e}var o={},t={index:0},i=[];function r(e){return s.p+"static/js/"+({"pages-accountLogin-accountLogin":"pages-accountLogin-accountLogin","pages-binding-email-binding-email":"pages-binding-email-binding-email","pages-collection-collection":"pages-collection-collection","pages-delivery-address-delivery-address":"pages-delivery-address-delivery-address","pages-editAddress-editAddress":"pages-editAddress-editAddress","pages-index-index":"pages-index-index","pages-login-login":"pages-login-login","pages-logs-logs":"pages-logs-logs","pages-news-detail-news-detail":"pages-news-detail-news-detail","pages-recent-news-recent-news":"pages-recent-news-recent-news","pages-register-register":"pages-register-register","pages-search-page-search-page":"pages-search-page-search-page","pages-search-prod-show-search-prod-show":"pages-search-prod-show-search-prod-show","pages-user-user":"pages-user-user","pages-userInfo-userInfo":"pages-userInfo-userInfo"}[e]||e)+"."+{"pages-accountLogin-accountLogin":"3d096484","pages-binding-email-binding-email":"fed67bae","pages-collection-collection":"77edfcba","pages-delivery-address-delivery-address":"25dc39be","pages-editAddress-editAddress":"759a80c7","pages-index-index":"6b8cecef","pages-login-login":"d5af1c20","pages-logs-logs":"195f44e2","pages-news-detail-news-detail":"21d81b2a","pages-recent-news-recent-news":"3d8b4a42","pages-register-register":"f323d99b","pages-search-page-search-page":"ee11a312","pages-search-prod-show-search-prod-show":"dac5807a","pages-user-user":"12b1d21e","pages-userInfo-userInfo":"1db14923"}[e]+".js"}function s(n){if(o[n])return o[n].exports;var a=o[n]={i:n,l:!1,exports:{}};return e[n].call(a.exports,a,a.exports,s),a.l=!0,a.exports}s.e=function(e){var n=[],a=t[e];if(0!==a)if(a)n.push(a[2]);else{var o=new Promise((function(n,o){a=t[e]=[n,o]}));n.push(a[2]=o);var i,c=document.createElement("script");c.charset="utf-8",c.timeout=120,s.nc&&c.setAttribute("nonce",s.nc),c.src=r(e);var g=new Error;i=function(n){c.onerror=c.onload=null,clearTimeout(u);var a=t[e];if(0!==a){if(a){var o=n&&("load"===n.type?"missing":n.type),i=n&&n.target&&n.target.src;g.message="Loading chunk "+e+" failed.\n("+o+": "+i+")",g.name="ChunkLoadError",g.type=o,g.request=i,a[1](g)}t[e]=void 0}};var u=setTimeout((function(){i({type:"timeout",target:c})}),12e4);c.onerror=c.onload=i,document.head.appendChild(c)}return Promise.all(n)},s.m=e,s.c=o,s.d=function(e,n,a){s.o(e,n)||Object.defineProperty(e,n,{enumerable:!0,get:a})},s.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},s.t=function(e,n){if(1&n&&(e=s(e)),8&n)return e;if(4&n&&"object"===typeof e&&e&&e.__esModule)return e;var a=Object.create(null);if(s.r(a),Object.defineProperty(a,"default",{enumerable:!0,value:e}),2&n&&"string"!=typeof e)for(var o in e)s.d(a,o,function(n){return e[n]}.bind(null,o));return a},s.n=function(e){var n=e&&e.__esModule?function(){return e["default"]}:function(){return e};return s.d(n,"a",n),n},s.o=function(e,n){return Object.prototype.hasOwnProperty.call(e,n)},s.p="/",s.oe=function(e){throw console.error(e),e};var c=window["webpackJsonp"]=window["webpackJsonp"]||[],g=c.push.bind(c);c.push=n,c=c.slice();for(var u=0;u<c.length;u++)n(c[u]);var d=g;i.push([0,"chunk-vendors"]),a()})({0:function(e,n,a){e.exports=a("6ed6")},"3c78":function(e,n,a){"use strict";var o=a("6fca"),t=a.n(o);t.a},"522b":function(e,n,a){"use strict";a.r(n);var o=a("52ad"),t=a("d7a9");for(var i in t)"default"!==i&&function(e){a.d(n,e,(function(){return t[e]}))}(i);a("3c78");var r,s=a("f0c5"),c=Object(s["a"])(t["default"],o["b"],o["c"],!1,null,null,null,!1,o["a"],r);n["default"]=c.exports},"52ad":function(e,n,a){"use strict";var o;a.d(n,"b",(function(){return t})),a.d(n,"c",(function(){return i})),a.d(n,"a",(function(){return o}));var t=function(){var e=this,n=e.$createElement,a=e._self._c||n;return a("App",{attrs:{keepAliveInclude:e.keepAliveInclude}})},i=[]},"65b4":function(e,n,a){a("d81d"),a("d3b7"),a("4d63"),a("ac1f"),a("25f0"),a("5319");var o=function(e){var n=e.getFullYear(),a=e.getMonth()+1,o=e.getDate(),i=e.getHours(),r=e.getMinutes(),s=e.getSeconds();return[n,a,o].map(t).join("/")+" "+[i,r,s].map(t).join(":")},t=function(e){return e=e.toString(),e[1]?e:"0"+e},i=function(e){return e=e.replace(/\<img/gi,'<img style="width:100% !important;height:auto !important;margin:0;display:flex;" '),e=e.replace(/\<td/gi,'<td  cellspacing="0" cellpadding="0" border="0" style="display:block;vertical-align:top;margin: 0px; padding: 0px; border: 0px;outline-width:0px;" '),e=e.replace(/width=/gi,"sss="),e=e.replace(/height=/gi,"sss="),e=e.replace(/ \/\>/gi,' style="max-width:100% !important;height:auto !important;margin:0;display:block;" />'),e},r=function(e){return decodeURIComponent((new RegExp("[?|&]"+e+"=([^&;]+?)(&|#|;|$)").exec(location.href)||["",""])[1].replace(/\+/g,"%20"))||null},s=function(){var e="";uni.removeTabBarBadge({index:"mp"==e?3:2})};e.exports={formatTime:o,getUrlKey:r,formatHtml:i,removeTabBadge:s}},"6ed6":function(e,n,a){"use strict";var o=a("4ea4");a("4160"),a("b64b"),a("ac1f"),a("1276"),a("159b");var t=o(a("5530"));a("e260"),a("e6cf"),a("cca6"),a("a79d"),a("fcfc"),a("1c31");var i=o(a("e143")),r=o(a("522b"));i.default.config.productionTip=!1,i.default.mixin({methods:{setData:function(e){var n,a,o=this,t=[];Object.keys(e).forEach((function(i){t=i.split("."),n=e[i],a=o.$data,t.forEach((function(e,i){i+1==t.length?o.$set(a,e,n):a[e]||o.$set(a,e,{}),a=a[e]}))}))}}}),r.default.mpType="app";var s=new i.default((0,t.default)({},r.default));s.$mount()},"6fca":function(e,n,a){var o=a("d19b");"string"===typeof o&&(o=[[e.i,o,""]]),o.locals&&(e.exports=o.locals);var t=a("4f06").default;t("0db8374f",o,!0,{sourceMap:!1,shadowMode:!1})},a458:function(e,n){var a="wx42bbe857570c5b40",o="http://127.0.0.1:9581",t="ws://127.0.0.1:9581/api";n.domain=o,n.wsDomain=t,n.mpAppId=a},b589:function(e,n){var a={SCOREPAY:0,WECHATPAY:1,ALIPAY:2,WECHATPAY_SWEEP_CODE:3,WECHATPAY_H5:4,WECHATPAY_MP:5,ALIPAY_H5:6,ALIPAY_APP:7,WECHATPAY_APP:8,BALANCEPAY:9},o={MINI:1,MP:2,PC:3,H5:4,ANDROID:5,IOS:6,ALI:7,ALIMINI:8};e.exports={PayType:a,AppType:o}},ceed:function(e,n,a){"use strict";a("c975"),a("ac1f"),a("841c"),Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0;var o=a("f813"),t=a("65b4"),i={onLaunach:function(){},onShow:function(){var e=navigator.userAgent.toLowerCase();e.search(/MicroMessenger/i)>-1&&!uni.getStorageSync("appType")&&(uni.setStorageSync("appType",2),o.mpAuthLogin());var n=t.getUrlKey("state"),a=t.getUrlKey("code");if(("needCode"==n||"unNeedCode"==n)&&a){var i=window.location.href;i.indexOf("code=")>0&&i.indexOf("&state=unNeedCode")>-1&&(o.mpLogin(null,a),i=i.substring(0,i.indexOf("code=")-1),history.replaceState({},"",i)),o.getCartCount()}},globalData:{requestQueue:[],isLanding:!1,totalCartCount:0},methods:{}};n.default=i},d19b:function(e,n,a){var o=a("24fb");n=o(!1),n.push([e.i,"/**app.wxss**/.container{height:100%;box-sizing:border-box;color:#333;font-family:helvetica,Heiti SC,PingFangSC-Light}.price{font-family:Arial;display:inline-block;color:#eb2444;padding-bottom:%?10?%;padding-left:%?10?%}\r\n/* 价格数字显示不同大小 */.symbol{font-size:%?24?%}.big-num{font-size:%?32?%}.small-num{font-size:%?24?%}\r\n/* \r\n*改变checkbox样式\r\n*自定义样式\r\n*/\r\n/* reg\t  */uni-checkbox-group{width:100%!important}uni-checkbox-group uni-label{width:33%!important;display:inline-flex;margin-bottom:%?20?%}\r\n/*checkbox 选项框大小  */uni-checkbox .uni-checkbox-input{width:%?38?%!important;height:%?38?%!important;border-radius:50%!important}\r\n/*checkbox选中后样式  */uni-checkbox .uni-checkbox-input.uni-checkbox-input-checked{background:#e43130;border:1px solid transparent!important}\r\n/*checkbox选中后图标样式  */uni-checkbox .uni-checkbox-input.uni-checkbox-input-checked::before{display:inline-block;width:%?20?%;height:%?20?%;line-height:%?20?%;text-align:center;font-size:%?18?%;color:#fff;background:transparent;transform:translate(-60%,-50%) scale(1);-webkit-transform:translate(-60%,-50%) scale(1)}\r\n/* \r\n*改变radio样式\r\n*自定义样式\r\n*/\r\n/* 未选中的 背景样式 */uni-radio .uni-radio-input{height:%?36?%;width:%?36?%;border-radius:50%;background:transparent;box-sizing:border-box}\r\n/* 选中后的 背景样式 */uni-radio .uni-radio-input.uni-radio-input-checked{border:none!important;background:#e43130!important}\r\n/* 选中后的 对勾样式 */uni-radio .uni-radio-input.uni-radio-input-checked::before{border-radius:50%;width:%?32?%;height:%?32?%;line-height:%?32?%;text-align:center;font-size:%?20?%;color:#fff;background:#e43130;border-radius:50%;transform:translate(-50%,-50%) scale(1);-webkit-transform:translate(-50%,-50%) scale(1)}\r\n/* 底部按钮兼容 iPhone X以上 */@media screen and (width:375px) and (height:812px){.container{padding-bottom:70px}}@media screen and (width:414px) and (height:736px){.container{padding-bottom:70px}}\r\n/* 隐藏头部 */uni-page-head{display:none}\r\n/* 轮播图指示点 */uni-swiper .uni-swiper-dots-horizontal{bottom:20px!important}",""]),e.exports=n},d7a9:function(e,n,a){"use strict";a.r(n);var o=a("ceed"),t=a.n(o);for(var i in o)"default"!==i&&function(e){a.d(n,e,(function(){return o[e]}))}(i);n["default"]=t.a},f813:function(e,n,a){"use strict";a("99af"),a("d3b7"),a("ac1f"),a("25f0"),a("841c");var o=a("b589"),t=a("a458");a("65b4");function i(e,n){getApp().globalData;"[object Array]"==Object.prototype.toString.call(e.data)?e.data=JSON.stringify(e.data):"[object Number]"==Object.prototype.toString.call(e.data)&&(e.data=e.data+"");var a=!1;if(e.needToken){if(!uni.getStorageSync("token")||"undefined"==typeof uni.getStorageSync("token"))return;a=e.needToken}wx.request({url:(e.domain?e.domain:t.domain)+e.url,data:e.data,header:{Authorization:a?uni.getStorageSync("token"):void 0},method:void 0==e.method?"POST":e.method,dataType:"json",responseType:void 0==e.responseType?"text":e.responseType,success:function(n){if(200==n.statusCode||201==n.statusCode||204==n.statusCode)e.callBack&&e.callBack(n.data);else if(500==n.statusCode)uni.showToast({title:n.data.message,icon:"none"});else if(401==n.statusCode){uni.removeStorageSync("loginResult"),uni.removeStorageSync("token");var a=navigator.userAgent.toLowerCase();a.search(/MicroMessenger/i)>-1&&uni.setStorageSync("appType",o.AppType.MP),uni.hideLoading(),e.dontTrunLogin||(uni.getStorageSync("hadLogin")?uni.showModal({title:"提示",content:"登录已过期",cancelText:"取消",confirmText:"确定",success:function(e){if(e.confirm){var n="";n=uni.getStorageSync("appType")==o.AppType.MP||uni.getStorageSync("appType")==o.AppType.MINI?"/pages/login/login":"/pages/accountLogin/accountLogin",uni.navigateTo({url:n})}else uni.switchTab({url:"/pages/index/index"})}}):uni.showModal({title:"提示",content:"登录已过期",cancelText:"取消",confirmText:"确定",success:function(e){e.confirm?uni.navigateTo({url:uni.getStorageSync("appType")==o.AppType.MP?"/pages/login/login":"/pages/accountLogin/accountLogin"}):uni.switchTab({url:"/pages/index/index"})}}))}else 400!=n.statusCode||e.errCallBack?(e.errCallBack&&e.errCallBack(n),uni.hideLoading()):(uni.hideLoading(),uni.showToast({title:n.data,icon:"none"}))},fail:function(e){uni.hideLoading(),"request:fail abort"!=e.errMsg?setTimeout((function(){uni.showToast({title:"服务器出了点小差",icon:"none"})}),1):console.log("请求被取消啦~")}})}var r=function(){uni.setStorageSync("token","bearer"+result.access_token)},s=function(e,n){i({login:!0,url:"/appLogin",data:{principal:n,appType:o.AppType.MP},callBack:function(n){c(n,e)}},!0)};function c(e,n){wx.setStorageSync("loginResult",e),wx.setStorageSync("hadLogin",!0),wx.setStorageSync("token",e.data),e.data?wx.setStorageSync("hadBindUser",!0):wx.setStorageSync("hadBindUser",!1),n&&n()}function g(){uni.getUserInfo({success:function(e){var n=JSON.parse(e.rawData);i({url:"/p/user/setUserInfo",method:"PUT",data:{avatarUrl:n.avatarUrl,nickName:n.nickName}})}})}function u(e,n){var a=null;if(e){var o=window.location,i=o.protocol,r=o.host;o.pathname,o.hash,a="".concat(i,"//").concat(r)+e}else a=window.location.href;var s="snsapi_userinfo";window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+t.mpAppId+"&redirect_uri="+encodeURIComponent(a)+"&response_type=code&scope="+s+"&state="+(n?"needCode":"unNeedCode")+"#wechat_redirect"}n.getToken=r,n.request=i,n.updateUserInfo=g,n.mpAuthLogin=u,n.loginSuccess=c,n.mpLogin=s},fcfc:function(e,n,a){"use strict";(function(e){var n=a("4ea4");a("13d5"),a("d3b7"),a("ac1f"),a("5319"),a("ddb0");var o=n(a("e143")),t={keys:function(){return[]}};e["____5A38E14____"]=!0,delete e["____5A38E14____"],e.__uniConfig={tabBar:{selectedColor:"#3a86b9",color:"#b8b8b8",list:[{pagePath:"pages/index/index",text:"首页",iconPath:"/static/images/tabbar/homepage.png",selectedIconPath:"/static/images/tabbar/homepage-sel.png",redDot:!1,badge:""},{pagePath:"pages/user/user",text:"我的",iconPath:"/static/images/tabbar/user.png",selectedIconPath:"/static/images/tabbar/user-sel.png",redDot:!1,badge:""}],backgroundColor:"",borderStyle:"black"},sitemapLocation:"sitemap.json",globalStyle:{backgroundTextStyle:"light",navigationBarBackgroundColor:"#fff",navigationBarTitleText:"WeChat",navigationBarTextStyle:"black"}},e.__uniConfig.compilerVersion="3.2.9",e.__uniConfig.router={mode:"history",base:"/"},e.__uniConfig.publicPath="/",e.__uniConfig["async"]={loading:"AsyncLoading",error:"AsyncError",delay:200,timeout:6e4},e.__uniConfig.debug=!1,e.__uniConfig.networkTimeout={request:6e4,connectSocket:6e4,uploadFile:6e4,downloadFile:6e4},e.__uniConfig.sdkConfigs={maps:{}},e.__uniConfig.qqMapKey="XVXBZ-NDMC4-JOGUS-XGIEE-QVHDZ-AMFV2",e.__uniConfig.locale="",e.__uniConfig.fallbackLocale=void 0,e.__uniConfig.locales=t.keys().reduce((function(e,n){var a=n.replace(/\.\/(uni-app.)?(.*).json/,"$2"),o=t(n);return Object.assign(e[a]||(e[a]={}),o.common||o),e}),{}),e.__uniConfig.nvue={"flex-direction":"column"},e.__uniConfig.__webpack_chunk_load__=a.e,o.default.component("pages-index-index",(function(e){var n={component:a.e("pages-index-index").then(function(){return e(a("db8f"))}.bind(null,a)).catch(a.oe),delay:__uniConfig["async"].delay,timeout:__uniConfig["async"].timeout};return __uniConfig["async"]["loading"]&&(n.loading={name:"SystemAsyncLoading",render:function(e){return e(__uniConfig["async"]["loading"])}}),__uniConfig["async"]["error"]&&(n.error={name:"SystemAsyncError",render:function(e){return e(__uniConfig["async"]["error"])}}),n})),o.default.component("pages-logs-logs",(function(e){var n={component:a.e("pages-logs-logs").then(function(){return e(a("72a6"))}.bind(null,a)).catch(a.oe),delay:__uniConfig["async"].delay,timeout:__uniConfig["async"].timeout};return __uniConfig["async"]["loading"]&&(n.loading={name:"SystemAsyncLoading",render:function(e){return e(__uniConfig["async"]["loading"])}}),__uniConfig["async"]["error"]&&(n.error={name:"SystemAsyncError",render:function(e){return e(__uniConfig["async"]["error"])}}),n})),o.default.component("pages-user-user",(function(e){var n={component:a.e("pages-user-user").then(function(){return e(a("5392"))}.bind(null,a)).catch(a.oe),delay:__uniConfig["async"].delay,timeout:__uniConfig["async"].timeout};return __uniConfig["async"]["loading"]&&(n.loading={name:"SystemAsyncLoading",render:function(e){return e(__uniConfig["async"]["loading"])}}),__uniConfig["async"]["error"]&&(n.error={name:"SystemAsyncError",render:function(e){return e(__uniConfig["async"]["error"])}}),n})),o.default.component("pages-search-page-search-page",(function(e){var n={component:a.e("pages-search-page-search-page").then(function(){return e(a("0459"))}.bind(null,a)).catch(a.oe),delay:__uniConfig["async"].delay,timeout:__uniConfig["async"].timeout};return __uniConfig["async"]["loading"]&&(n.loading={name:"SystemAsyncLoading",render:function(e){return e(__uniConfig["async"]["loading"])}}),__uniConfig["async"]["error"]&&(n.error={name:"SystemAsyncError",render:function(e){return e(__uniConfig["async"]["error"])}}),n})),o.default.component("pages-delivery-address-delivery-address",(function(e){var n={component:a.e("pages-delivery-address-delivery-address").then(function(){return e(a("f112"))}.bind(null,a)).catch(a.oe),delay:__uniConfig["async"].delay,timeout:__uniConfig["async"].timeout};return __uniConfig["async"]["loading"]&&(n.loading={name:"SystemAsyncLoading",render:function(e){return e(__uniConfig["async"]["loading"])}}),__uniConfig["async"]["error"]&&(n.error={name:"SystemAsyncError",render:function(e){return e(__uniConfig["async"]["error"])}}),n})),o.default.component("pages-editAddress-editAddress",(function(e){var n={component:a.e("pages-editAddress-editAddress").then(function(){return e(a("ea1c"))}.bind(null,a)).catch(a.oe),delay:__uniConfig["async"].delay,timeout:__uniConfig["async"].timeout};return __uniConfig["async"]["loading"]&&(n.loading={name:"SystemAsyncLoading",render:function(e){return e(__uniConfig["async"]["loading"])}}),__uniConfig["async"]["error"]&&(n.error={name:"SystemAsyncError",render:function(e){return e(__uniConfig["async"]["error"])}}),n})),o.default.component("pages-binding-email-binding-email",(function(e){var n={component:a.e("pages-binding-email-binding-email").then(function(){return e(a("52e2"))}.bind(null,a)).catch(a.oe),delay:__uniConfig["async"].delay,timeout:__uniConfig["async"].timeout};return __uniConfig["async"]["loading"]&&(n.loading={name:"SystemAsyncLoading",render:function(e){return e(__uniConfig["async"]["loading"])}}),__uniConfig["async"]["error"]&&(n.error={name:"SystemAsyncError",render:function(e){return e(__uniConfig["async"]["error"])}}),n})),o.default.component("pages-search-prod-show-search-prod-show",(function(e){var n={component:a.e("pages-search-prod-show-search-prod-show").then(function(){return e(a("dfcb"))}.bind(null,a)).catch(a.oe),delay:__uniConfig["async"].delay,timeout:__uniConfig["async"].timeout};return __uniConfig["async"]["loading"]&&(n.loading={name:"SystemAsyncLoading",render:function(e){return e(__uniConfig["async"]["loading"])}}),__uniConfig["async"]["error"]&&(n.error={name:"SystemAsyncError",render:function(e){return e(__uniConfig["async"]["error"])}}),n})),o.default.component("pages-login-login",(function(e){var n={component:a.e("pages-login-login").then(function(){return e(a("87c1"))}.bind(null,a)).catch(a.oe),delay:__uniConfig["async"].delay,timeout:__uniConfig["async"].timeout};return __uniConfig["async"]["loading"]&&(n.loading={name:"SystemAsyncLoading",render:function(e){return e(__uniConfig["async"]["loading"])}}),__uniConfig["async"]["error"]&&(n.error={name:"SystemAsyncError",render:function(e){return e(__uniConfig["async"]["error"])}}),n})),o.default.component("pages-recent-news-recent-news",(function(e){var n={component:a.e("pages-recent-news-recent-news").then(function(){return e(a("7235"))}.bind(null,a)).catch(a.oe),delay:__uniConfig["async"].delay,timeout:__uniConfig["async"].timeout};return __uniConfig["async"]["loading"]&&(n.loading={name:"SystemAsyncLoading",render:function(e){return e(__uniConfig["async"]["loading"])}}),__uniConfig["async"]["error"]&&(n.error={name:"SystemAsyncError",render:function(e){return e(__uniConfig["async"]["error"])}}),n})),o.default.component("pages-news-detail-news-detail",(function(e){var n={component:a.e("pages-news-detail-news-detail").then(function(){return e(a("8e5d"))}.bind(null,a)).catch(a.oe),delay:__uniConfig["async"].delay,timeout:__uniConfig["async"].timeout};return __uniConfig["async"]["loading"]&&(n.loading={name:"SystemAsyncLoading",render:function(e){return e(__uniConfig["async"]["loading"])}}),__uniConfig["async"]["error"]&&(n.error={name:"SystemAsyncError",render:function(e){return e(__uniConfig["async"]["error"])}}),n})),o.default.component("pages-accountLogin-accountLogin",(function(e){var n={component:a.e("pages-accountLogin-accountLogin").then(function(){return e(a("6c9a"))}.bind(null,a)).catch(a.oe),delay:__uniConfig["async"].delay,timeout:__uniConfig["async"].timeout};return __uniConfig["async"]["loading"]&&(n.loading={name:"SystemAsyncLoading",render:function(e){return e(__uniConfig["async"]["loading"])}}),__uniConfig["async"]["error"]&&(n.error={name:"SystemAsyncError",render:function(e){return e(__uniConfig["async"]["error"])}}),n})),o.default.component("pages-register-register",(function(e){var n={component:a.e("pages-register-register").then(function(){return e(a("32bd"))}.bind(null,a)).catch(a.oe),delay:__uniConfig["async"].delay,timeout:__uniConfig["async"].timeout};return __uniConfig["async"]["loading"]&&(n.loading={name:"SystemAsyncLoading",render:function(e){return e(__uniConfig["async"]["loading"])}}),__uniConfig["async"]["error"]&&(n.error={name:"SystemAsyncError",render:function(e){return e(__uniConfig["async"]["error"])}}),n})),o.default.component("pages-userInfo-userInfo",(function(e){var n={component:a.e("pages-userInfo-userInfo").then(function(){return e(a("f8ff"))}.bind(null,a)).catch(a.oe),delay:__uniConfig["async"].delay,timeout:__uniConfig["async"].timeout};return __uniConfig["async"]["loading"]&&(n.loading={name:"SystemAsyncLoading",render:function(e){return e(__uniConfig["async"]["loading"])}}),__uniConfig["async"]["error"]&&(n.error={name:"SystemAsyncError",render:function(e){return e(__uniConfig["async"]["error"])}}),n})),o.default.component("pages-collection-collection",(function(e){var n={component:a.e("pages-collection-collection").then(function(){return e(a("cff2"))}.bind(null,a)).catch(a.oe),delay:__uniConfig["async"].delay,timeout:__uniConfig["async"].timeout};return __uniConfig["async"]["loading"]&&(n.loading={name:"SystemAsyncLoading",render:function(e){return e(__uniConfig["async"]["loading"])}}),__uniConfig["async"]["error"]&&(n.error={name:"SystemAsyncError",render:function(e){return e(__uniConfig["async"]["error"])}}),n})),e.__uniRoutes=[{path:"/",alias:"/pages/index/index",component:{render:function(e){return e("Page",{props:Object.assign({isQuit:!0,isEntry:!0,isTabBar:!0,tabBarIndex:0},__uniConfig.globalStyle,{backgroundTextStyle:"dark",navigationBarBackgroundColor:"white",navigationBarTextStyle:"black",enablePullDownRefresh:!0,navigationBarTitleText:"采花科技"})},[e("pages-index-index",{slot:"page"})])}},meta:{id:1,name:"pages-index-index",isNVue:!1,maxWidth:0,pagePath:"pages/index/index",isQuit:!0,isEntry:!0,isTabBar:!0,tabBarIndex:0,windowTop:44}},{path:"/pages/logs/logs",component:{render:function(e){return e("Page",{props:Object.assign({},__uniConfig.globalStyle,{navigationBarTitleText:"查看启动日志"})},[e("pages-logs-logs",{slot:"page"})])}},meta:{name:"pages-logs-logs",isNVue:!1,maxWidth:0,pagePath:"pages/logs/logs",windowTop:44}},{path:"/pages/user/user",component:{render:function(e){return e("Page",{props:Object.assign({isQuit:!0,isTabBar:!0,tabBarIndex:1},__uniConfig.globalStyle,{navigationBarTitleText:"个人中心"})},[e("pages-user-user",{slot:"page"})])}},meta:{id:2,name:"pages-user-user",isNVue:!1,maxWidth:0,pagePath:"pages/user/user",isQuit:!0,isTabBar:!0,tabBarIndex:1,windowTop:44}},{path:"/pages/search-page/search-page",component:{render:function(e){return e("Page",{props:Object.assign({},__uniConfig.globalStyle,{backgroundTextStyle:"light",navigationBarBackgroundColor:"#fff",navigationBarTitleText:"搜索",navigationBarTextStyle:"black"})},[e("pages-search-page-search-page",{slot:"page"})])}},meta:{name:"pages-search-page-search-page",isNVue:!1,maxWidth:0,pagePath:"pages/search-page/search-page",windowTop:44}},{path:"/pages/delivery-address/delivery-address",component:{render:function(e){return e("Page",{props:Object.assign({},__uniConfig.globalStyle,{backgroundTextStyle:"light",navigationBarBackgroundColor:"#fff",navigationBarTitleText:"收货地址",navigationBarTextStyle:"black"})},[e("pages-delivery-address-delivery-address",{slot:"page"})])}},meta:{name:"pages-delivery-address-delivery-address",isNVue:!1,maxWidth:0,pagePath:"pages/delivery-address/delivery-address",windowTop:44}},{path:"/pages/editAddress/editAddress",component:{render:function(e){return e("Page",{props:Object.assign({},__uniConfig.globalStyle,{backgroundTextStyle:"light",navigationBarBackgroundColor:"#fff",navigationBarTitleText:"编辑收货地址",navigationBarTextStyle:"black"})},[e("pages-editAddress-editAddress",{slot:"page"})])}},meta:{name:"pages-editAddress-editAddress",isNVue:!1,maxWidth:0,pagePath:"pages/editAddress/editAddress",windowTop:44}},{path:"/pages/binding-email/binding-email",component:{render:function(e){return e("Page",{props:Object.assign({},__uniConfig.globalStyle,{backgroundTextStyle:"light",navigationBarBackgroundColor:"#fff",navigationBarTitleText:"换绑手机号",navigationBarTextStyle:"black"})},[e("pages-binding-email-binding-email",{slot:"page"})])}},meta:{name:"pages-binding-email-binding-email",isNVue:!1,maxWidth:0,pagePath:"pages/binding-email/binding-email",windowTop:44}},{path:"/pages/search-prod-show/search-prod-show",component:{render:function(e){return e("Page",{props:Object.assign({},__uniConfig.globalStyle,{backgroundTextStyle:"light",navigationBarBackgroundColor:"#fff",navigationBarTitleText:"搜索结果",navigationBarTextStyle:"black"})},[e("pages-search-prod-show-search-prod-show",{slot:"page"})])}},meta:{name:"pages-search-prod-show-search-prod-show",isNVue:!1,maxWidth:0,pagePath:"pages/search-prod-show/search-prod-show",windowTop:44}},{path:"/pages/login/login",component:{render:function(e){return e("Page",{props:Object.assign({},__uniConfig.globalStyle,{})},[e("pages-login-login",{slot:"page"})])}},meta:{name:"pages-login-login",isNVue:!1,maxWidth:0,pagePath:"pages/login/login",windowTop:44}},{path:"/pages/recent-news/recent-news",component:{render:function(e){return e("Page",{props:Object.assign({},__uniConfig.globalStyle,{backgroundTextStyle:"light",navigationBarTitleText:"最新公告",navigationBarTextStyle:"black",navigationBarBackgroundColor:"#fafafa"})},[e("pages-recent-news-recent-news",{slot:"page"})])}},meta:{name:"pages-recent-news-recent-news",isNVue:!1,maxWidth:0,pagePath:"pages/recent-news/recent-news",windowTop:44}},{path:"/pages/news-detail/news-detail",component:{render:function(e){return e("Page",{props:Object.assign({},__uniConfig.globalStyle,{backgroundTextStyle:"light",navigationBarTitleText:"文章详情",navigationBarTextStyle:"black",navigationBarBackgroundColor:"#fafafa"})},[e("pages-news-detail-news-detail",{slot:"page"})])}},meta:{name:"pages-news-detail-news-detail",isNVue:!1,maxWidth:0,pagePath:"pages/news-detail/news-detail",windowTop:44}},{path:"/pages/accountLogin/accountLogin",component:{render:function(e){return e("Page",{props:Object.assign({},__uniConfig.globalStyle,{})},[e("pages-accountLogin-accountLogin",{slot:"page"})])}},meta:{name:"pages-accountLogin-accountLogin",isNVue:!1,maxWidth:0,pagePath:"pages/accountLogin/accountLogin",windowTop:44}},{path:"/pages/register/register",component:{render:function(e){return e("Page",{props:Object.assign({},__uniConfig.globalStyle,{})},[e("pages-register-register",{slot:"page"})])}},meta:{name:"pages-register-register",isNVue:!1,maxWidth:0,pagePath:"pages/register/register",windowTop:44}},{path:"/pages/userInfo/userInfo",component:{render:function(e){return e("Page",{props:Object.assign({},__uniConfig.globalStyle,{navigationBarTitleText:"个人资料",enablePullDownRefresh:!1})},[e("pages-userInfo-userInfo",{slot:"page"})])}},meta:{name:"pages-userInfo-userInfo",isNVue:!1,maxWidth:0,pagePath:"pages/userInfo/userInfo",windowTop:44}},{path:"/pages/collection/collection",component:{render:function(e){return e("Page",{props:Object.assign({},__uniConfig.globalStyle,{navigationBarTitleText:"",enablePullDownRefresh:!1})},[e("pages-collection-collection",{slot:"page"})])}},meta:{name:"pages-collection-collection",isNVue:!1,maxWidth:0,pagePath:"pages/collection/collection",windowTop:44}},{path:"/preview-image",component:{render:function(e){return e("Page",{props:{navigationStyle:"custom"}},[e("system-preview-image",{slot:"page"})])}},meta:{name:"preview-image",pagePath:"/preview-image"}},{path:"/choose-location",component:{render:function(e){return e("Page",{props:{navigationStyle:"custom"}},[e("system-choose-location",{slot:"page"})])}},meta:{name:"choose-location",pagePath:"/choose-location"}},{path:"/open-location",component:{render:function(e){return e("Page",{props:{navigationStyle:"custom"}},[e("system-open-location",{slot:"page"})])}},meta:{name:"open-location",pagePath:"/open-location"}}],e.UniApp&&new e.UniApp}).call(this,a("c8ba"))}});