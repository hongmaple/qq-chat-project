import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
	state: {
		socketTask: null,
		eventlist: {},
		unread: []
	},
	mutations: {
		WEBSOCKET_INIT(state, url) {
			// 创建一个this.socketTask对象【发送、接收、关闭socket都由这个对象操作】
			state.socketTask = uni.connectSocket({
				url,
				// 【非常重要】必须确保你的服务器是成功的,如果是手机测试千万别使用ws://127.0.0.1:9099【特别容易犯的错误】
				success(data) {
					console.log("websocket连接成功");
				},
			});
			// 消息的发送和接收必须在正常连接打开中,才能发送或接收【否则会失败】
			state.socketTask.onOpen((res) => {
				console.log("WebSocket连接正常打开中...！");
				state.is_open_socket = true;
				// 注：只有连接正常打开中 ，才能正常收到消息
				state.socketTask.onMessage((res) => {
					//console.log("收到服务器内容：" , JSON.parse(res.data));
					let message = JSON.parse(res.data);
					state.eventlist = message.data;
					// state.unread.push(JSON.parse(res.data))
				});
			})
		},
		WEBSOCKET_SEND(state, p) {
			console.log("ws发送！");
			state.socketTask.send({
				data: p,
				async success() {
					console.log("消息发送成功");
				},
			});
		},
	},


	actions: {
		WEBSOCKET_INIT({
			commit
		}, url) {
			commit('WEBSOCKET_INIT', url)
		},
		WEBSOCKET_SEND({
			commit
		}, p) {
			commit('WEBSOCKET_SEND', p)
		}
	}
})

