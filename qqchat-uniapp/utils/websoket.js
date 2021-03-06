var config = require("./config.js"); //统一的网络请求方法

/**
 * @param {Object} id 当前用户id
 * 打开websocket
 */
function openSocket(id) {
				if (typeof(WebSocket) == "undefined") {
					console.log("您的浏览器不支持WebSocket");
				} else {
					console.log("您的浏览器支持WebSocket");
					//实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
					var socketUrl = config.wsDomain+"/ws/" + id;
					var socket = new WebSocket(socketUrl);
					// var socket = uni.connectSocket({
					//     url: socketUrl, //仅为示例，并非真实接口地址。
					//     complete: ()=> {}
					// });
					uni.setStorageSync('socket',socket);
					//打开事件
					socket.onopen = function() {
						console.log("websocket已打开");
						//socket.send("这是来自客户端的消息" + location.href + new Date());
					};
					//关闭事件
					socket.onclose = function() {
						console.log("websocket已关闭");
					};
					//发生了错误事件
					socket.onerror = function() {
						uni.showToast({
							title: "连接发生了错误！",
							icon: "none"
						})
					}
				}
				return socket;
}

function unique(arr){
 //Set数据结构，它类似于数组，其成员的值都是唯一的
 return Array.from(new Set(arr)); // 利用Array.from将Set结构转换成数组
}

exports.openSocket = openSocket;