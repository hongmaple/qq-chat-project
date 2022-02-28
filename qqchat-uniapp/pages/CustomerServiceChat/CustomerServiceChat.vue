<template>
	<view class="container">
		<!-- 自定义顶部导航栏 -->
		<view style="position: fixed;top: 0rpx;text-align: center;z-index: 99;background-color: #00A0EA;width: 100%;">
			<view style="text-align: center;padding-top: 20rpx;font-size: 40rpx;padding-bottom: 20rpx;">{{toUsername}}</view>
		</view>
		<!-- 聊天内容 -->
		<scroll-view :animation="scrollAnimationData" @tap="hideKey()" 
		    ref="myMsgDtail" id="scrollview" scroll-y="true" 
			:style="chatBodyBottom"
			:scroll-into-view="scrollIntoID"
			:scroll-top="scrollTop"
			:scroll-with-animation="false" class="scroll-view">
				<!-- overflow:scroll; -->
				<view class="flex-column-start"  v-for="(item, index) in messagesList" :key="index">
					<view style="margin-bottom: 100rpx;margin-top: 80rpx;">	
						<!-- 发送方 -->
						<view v-if="item.fromUserId==fromUserId" class="content-right">
							<view class="fromAvatarImage-right">
								<image :src="serverUrl + user.avatarImage" class="more-pic" mode="widthFix"></image>
							</view>
							<view class="nickname_content_right">
								<view class="nickname_left">{{dateFormat(item.sendTime)}}</view>
								<view class="nickname_right">{{user.username}}</view></br>
								<view v-if="item.msgType==='text'" class="text-bs-right" v-html="item.contentText"></view>
								<view v-if="item.msgType==='file'" class="file-bs-right" >
									<image v-if="imageExtension.includes(item.exp)" @tap="previewImage(serverUrl + item.contentText)" style="width: 300rpx;height: 300rpx;" :src="serverUrl + item.contentText">
									</image>
									<video v-else-if="videoExtension.includes(item.exp)" style="width: 500rpx;z-index: 1;" id="myVideo" :src="serverUrl + item.contentText"
									     enable-danmu danmu-btn controls></video>
								    <view v-else style="width: 500rpx;height: 150rpx;">
										<view style="width: 440rpx;height: 150rpx;background-color: #0081ff;border-radius: 10rpx;border: 2rpx solid #FFFFFF;padding-top: 10rpx;padding-left: 5rpx;float: left;">
											<text style="width: 350rpx;height: 150rpx;float: left;text-align: center;">{{splitGetStr(item.contentText,'/',1)}}</text>
											<image style="width: 90rpx;height: 138rpx;float: right;" src="../../static/images/icon/none-file.png"></image>
										</view>	
										<view style="float: right;width: 30rpx;height: 150rpx;margin-top: 120rpx;">
											<image @tap="downloadFile(serverUrl + item.contentText)" style="width: 30rpx;height: 30rpx;" src="../../static/images/icon/downLoad.png"></image>
										</view>
									</view>		  
								</view>
							</view>
						</view>
						
						<!-- 接收方 -->
						<view v-else class="content-left">
							<view class="fromAvatarImage-left">
								<image :src="serverUrl + toAvatarimage" class="more-pic" mode="widthFix"></image>
							</view>
							<view class="nickname_content_left">
								<view class="nickname_right">{{dateFormat(item.sendTime)}}</view>
								<view class="nickname_left">{{toUsername}}</view></br>
								<view v-if="item.msgType==='text'" class="text-bs-left" v-html="item.contentText"></view>
								<view v-if="item.msgType==='file'" class="file-bs-left" >
									<image v-if="imageExtension.includes(item.exp)" @tap="previewImage(serverUrl + item.contentText)" style="width: 300rpx;height: 300rpx;" :src="serverUrl + item.contentText">
									</image>
									<video v-else-if="videoExtension.includes(item.exp)" style="width: 500rpx;" id="myVideo" :src="serverUrl + item.contentText"
									     enable-danmu danmu-btn controls></video>
								    <view v-else style="width: 500rpx;height: 150rpx;">
										<view style="width: 440rpx;height: 150rpx;background-color: #0081ff;border-radius: 10rpx;border: 2rpx solid #FFFFFF;padding-top: 10rpx;padding-left: 5rpx;float: left;">
											<text style="width: 350rpx;height: 150rpx;float: left;text-align: center;">{{splitGetStr(item.contentText,'/',1)}}</text>
											<image style="width: 90rpx;height: 138rpx;float: right;" src="../../static/images/icon/none-file.png"></image>
										</view>	
										<view style="float: right;width: 30rpx;height: 150rpx;margin-top: 120rpx;">
											<image @tap="downloadFile(item.contentText)" style="width: 30rpx;height: 30rpx;" src="../../static/images/icon/downLoad.png"></image>
										</view>
									</view>	
								</view>
							</view>
						</view>
				    </view>
				</view>
		</scroll-view>
		<!-- 底部发送框 -->
		<view ref="foot" class="order-detail-footer" :animation="footAnimationData" :style="'bottom:'+ KeyboardHeight +'rpx;'">
			<view :animation="menuAnimationData" style="width: 100%;float: left;">
				<view style="width: 100%;height: auto;float: left;margin-top: 10rpx;">
					<textarea :focus="focus" fixed="true" maxlength="-1"  v-model="danmuValue" class="uni-input" type="text" placeholder="输入内容" />
					<view class="footer-box">
						<text class="buy-again" @tap="onSendMessage()">发送</text>
					</view>
				</view>
				<view style="width: 100%;height: auto;float: left;margin-top: 10rpx;">
					<view class="i-btn" @tap="uploadVideo()">
						<image src="/static/images/icon/video.png"></image>
					</view>
					<view class="i-btn" @tap="onUploadImage()">
						<image src="/static/images/icon/upload-image.png"></image>
					</view>
					<view class="i-btn" @tap="onShowEmoji()">
						<image v-if="showEmoji" src="/static/images/icon/emoji-sel.png"></image>
						<image v-else src="/static/images/icon/emoji.png"></image>
					</view>
				</view>
				<view v-if="showEmoji" style="width: 100%;height: auto;float: left;background-color: #FFFFFF;">
					<scroll-view scroll-y="true" style="height: 300rpx;">
							 <view v-for="(item, index) in emojiJson" :key="index">
								 <text class="emoji-btn" @tap="addEmoji(item)">{{item}}
					             </text>
							 </view>
					</scroll-view>
				</view>
			</view>
		</view>
	</view>
</template>
<script>
	var http = require("../../utils/http.js");
	var config = require('../../utils/config.js');
	//var websoket = require('../../utils/websoket.js')
	var util = require('../../utils/util.js')
	import { mapState } from 'vuex';
	import {
		emojiJson,
		imageExtension,
		videoExtension,
		DOCUMENTEXTENSION
	} from '../../utils/constant.js'
	import {
	   dateFormat,
	   sub,
	   splitGetStr
	} from '../../utils/util.js'
	import {
	   down
	} from '../../utils/dowenLoad.js'
	export default {
		data() {
			return {
				showEmoji: false,
				srcollHeight: 0,
				systemInfo: uni.getSystemInfoSync(),
				scrollIntoID: 'chatID_0',
				ready: false,
				chatSrcollBottom: 0,
				focus: false,
				footAnimationData: {},
				scrollAnimationData: {},
				menuAnimationData: {},
				KeyboardHeight: 0,
				scrollTop: 0,
				soket: {},
				messagesList: [],
				danmuValue: '',
				toUserId: '',
				toUsername: '',
				toAvatarimage: '../../static/images/icon/head04.png',
				user: {},
				fromUserId: 0,
				serverUrl: config.domain,
				emojiJson: emojiJson,
				imageExtension: imageExtension,
				videoExtension: videoExtension,
				DOCUMENTEXTENSION: DOCUMENTEXTENSION
			}
		},
		onLoad: function(options) {
			var ths = this;
			if (!uni.getStorageSync('token')||typeof(uni.getStorageSync('token')) == "undefined") {
				uni.redirectTo({
					url: '../accountLogin/accountLogin'
				}); //跳转到登录页
				return;
			}
			var user = uni.getStorageSync('loginResult');
			ths.setData({
				fromUserId: user.id,
				user: user,
				toUserId: options.toUserId,
				toUsername: options.toUsername,
				toAvatarimage: options.toAvatarimage
			});
			ths.loadMessage(options.toUserId);
			var socketUrl = config.wsDomain+"/ws/" + user.id;
			this.$store.dispatch('WEBSOCKET_INIT', socketUrl)
			this.$store.state.socketTask.onMessage((res) => {
                var message = JSON.parse(res.data);
				if(message.status==10400) {
					uni.showToast({
						title: '网络不给力，请检查网络连接',
						icon: "none"
					}); //弹出提示框
				}else if(message.status==13140) {
					//ths.loadMessage(options.toUserId);
					ths.handleOnMessage(message);
					ths.scrollBottom();
				}
			});
		},
		onShow() {
			if (!uni.getStorageSync('token')||typeof(uni.getStorageSync('token')) == "undefined") {
				uni.redirectTo({
					url: '../accountLogin/accountLogin'
				}); //跳转到登录页
				return;
			}
			this.onKeyboardHeight();
			// #ifdef H5
			this.srcollHeight = this.systemInfo.windowHeight+80;
			// #endif
			// #ifndef H5
			this.srcollHeight = this.systemInfo.screenHeight - this.systemInfo.statusBarHeight - this.safeBottom+80;
			// #endif
			// if(this.ready) {
			// 	this.scrollBottom();
			// }
			// this.$nextTick(function() {
			// 	that.getSession();
			// })
		},
		mounted() {
		    
		},
		updated() {
		    
		},
		methods: {
			onSendMessage: function() {
				var ths = this;
				let date = new Date().getTime();
				if(ths.danmuValue=='') {
					uni.showToast({
						title: '不能发送空消息',
						icon: "none"
					}); //弹出提示框
					return;
				}
				var sendMessages = {
					status: 13140,
					message: "发送消息",
					data:{
						contentText: ths.danmuValue,
						fromAvatarImage: ths.user.avatarImage,
						fromUserName: ths.user.username,
						fromUserId: ths.fromUserId,
						sendTime: date,
						toUserId: ths.toUserId,
						toUsername: ths.toUsername,
						toAvatarImage: ths.toAvatarimage,
						msgType: "text",
						exp: ""
					}
				}
				ths.sendMessage(sendMessages);
			},
			sendMessage: function(sendMessages) {
				let ths = this;
				uni.showLoading();
				var params = {
					url: "/messageList",
					method: "POST",
					needToken: true,
					data: sendMessages.data,
					callBack: function(res) {
						console.log(res)
						uni.hideLoading();
						ths.$store.dispatch('WEBSOCKET_SEND', JSON.stringify(sendMessages))
						ths.handleOnMessage(sendMessages);
						ths.scrollBottom();
						//ths.closeShowModer();
					}
				};
				http.request(params);
			},
			handleOnMessage: function(sendMessages) {
				let ths = this;
				var messages = [];
				messages.push(sendMessages.data);
				var list = ths.messagesList;
				if (ths.messagesList) {
					Array.prototype.push.apply(list, messages);
				} else {
					list = messages;
				}
				ths.setData({
					messagesList: list,
					danmuValue: ''
				});
				ths.scrollBottom();
			},
			loadMessage: function(toUserId) {
				var ths = this;
				var params = {
					url: "/messageList/"+toUserId,
					method: "get",
					needToken: true,
					data: {},
					callBack: function(res) {
						ths.setData({
						  messagesList: res.data,
						  ready: true
						});
						ths.scrollBottom();
					}
				};
				http.request(params);
			},
			scrollBottom: function() {
				var that = this;
				setTimeout(() => {
					if (that.messagesList.length) {
						if (that.scrollIntoID == "chatId_" + (that.messagesList.length - 1)) {
							that.scrollIntoID = "chatId_" + (that.messagesList.length - 2);
							that.scrollTop = that.srcollHeight + 2000;
							that.scrollBottom();
						} else if (that.scrollIntoID == "chatId_" + (that.messagesList.length - 2)) {
							that.scrollIntoID = "chatId_" + (that.messagesList.length - 1);
							that.scrollTop = that.srcollHeight + 2000;
						} else {
							that.scrollIntoID = "chatId_" + (that.messagesList.length - 1);
							that.scrollTop = that.srcollHeight + 1000;
						}
					} else {
						that.scrollIntoID = "chatId_0";
						scrollTop = 0;
					}
				}, 50)
			},
			onKeyboardHeight: function() {
				uni.onKeyboardHeightChange(res => {
					this.translatePageTop(res.height);
					if (this.systemInfo.platform == "android") {
						if (res.height > 100) {
							this.chatSrcollBottom = res.height - this.safeBottom;
							//this.inputScroll();
							//this.translatePageTop(res.height);
						} else {
							this.chatSrcollBottom = 0;
						}
					} else {
						if (res.height > 300) {
			
							this.chatSrcollBottom = res.height - this.safeBottom;
							//this.translatePageTop(res.height);
						} else {
							this.chatSrcollBottom = 0;
						}
					}
					if (res.height === 0) {
						//this.translatePageBottom();
						this.focus = false;
					}
					this.scrollBottom();
				})
			},
			translatePageTop: function(h) {
			
				// #ifndef  APP-NVUE
				var scrollAnimation = uni.createAnimation({
					duration: 220,
					timingFunction: 'ease',
				})
				 
				var footAnimation = uni.createAnimation({
					duration: 220,
					timingFunction: 'ease',
				})
				if(h == 0){
					scrollAnimation.translateY(-h).step()
					footAnimation.translateY(-h).step()
				}else{
					scrollAnimation.translateY(-h + 34).step()
					footAnimation.translateY(-h + 34).step()
				}
				 
				
				this.scrollAnimationData = scrollAnimation.export()
				this.footAnimationData = footAnimation.export()
				 
				// #endif
			
				// #ifdef  APP-NVUE
				let foot = this.getEl(this.$refs.foot);
				let scrollview = this.getEl(this.$refs.);
				h = h - this.safeBottom;
				animation.transition(scrollview, {
					styles: {
						transform: 'translateY(-' + h + ')',
					},
					duration: 220, //ms
					timingFunction: 'linear',
					needLayout: false,
					delay: 0 //ms
				}, function() {
			
				})
				animation.transition(foot, {
					styles: {
						transform: 'translateY(-' + h + ')',
					},
					duration: 220, //ms
					timingFunction: 'linear',
					needLayout: false,
					delay: 0 //ms
				}, function() {
			
				})
				// #endif
			
			
			
			},
			onShowEmoji: function() {
				let that=this
				if(that.showEmoji) {
					that.$refs.myMsgDtail.$el.style.paddingBottom='20%';
				}else {
					that.$refs.myMsgDtail.$el.style.paddingBottom='65%';
				}
				that.showEmoji = !that.showEmoji;
			},
			onUploadImage: function() {
				let ths = this;
				ths.closeShowModer();
				let date = new Date().getTime();
				uni.chooseImage({
					// extension: ths.extension,
				    success: (chooseImageRes) => {
				        const tempFilePaths = chooseImageRes.tempFilePaths;
				        const uploadTask = uni.uploadFile({
				            url: config.domain+"/common/upload", //仅为示例，非真实的接口地址
				            filePath: tempFilePaths[0],
				            name: 'file',
				            formData: {
				                
				            },
				            success: (uploadFileRes) => {
								var userInfo = this.userInfo;
								var data = JSON.parse(uploadFileRes.data);
								var fileNames = data.fileName.split(".");
								var sendMessages = {
									status: 13140,
									message: "发送消息",
									data:{
										contentText: data.fileName,
										fromAvatarImage: ths.user.avatarImage,
										fromUserName: ths.user.username,
										fromUserId: ths.fromUserId,
										sendTime: date,
										toUserId: ths.toUserId,
										toUsername: ths.toUsername,
										toAvatarImage: ths.toAvatarimage,
										msgType: "file",
										exp: fileNames[fileNames.length-1]
									}
								}
								ths.sendMessage(sendMessages);
								// ths.loadMessage(ths.toUserId);
				            }
				        });
				
				        uploadTask.onProgressUpdate((res) => {
				            console.log('上传进度' + res.progress);
				            console.log('已经上传的数据长度' + res.totalBytesSent);
				            console.log('预期需要上传的数据总长度' + res.totalBytesExpectedToSend);
				        });
				    }
				});
			},
			uploadVideo: function() {
				let ths = this;
				ths.closeShowModer();
				let date = new Date().getTime();
				uni.chooseVideo({
				    sourceType: ['camera', 'album'],
				    success: function (res) {
						const uploadTask = uni.uploadFile({
						    url: config.domain+"/common/upload", //仅为示例，非真实的接口地址
						    filePath: res.tempFilePath,
						    name: 'file',
						    formData: {
						        
						    },
						    success: (uploadFileRes) => {
								var userInfo = ths.userInfo;
								var data = JSON.parse(uploadFileRes.data);
								var fileNames = data.fileName.split(".");
								var sendMessages = {
									status: 13140,
									message: "发送消息",
									data:{
										contentText: data.fileName,
										fromAvatarImage: ths.user.avatarImage,
										fromUserName: ths.user.username,
										fromUserId: ths.fromUserId,
										sendTime: date,
										toUserId: ths.toUserId,
										toUsername: ths.toUsername,
										toAvatarImage: ths.toAvatarimage,
										msgType: "file",
										exp: fileNames[fileNames.length-1]
									}
								}
								ths.sendMessage(sendMessages);
								// ths.loadMessage(ths.toUserId);
						    }
						});
										
						uploadTask.onProgressUpdate((res) => {
						    console.log('上传进度' + res.progress);
						    console.log('已经上传的数据长度' + res.totalBytesSent);
						    console.log('预期需要上传的数据总长度' + res.totalBytesExpectedToSend);
						});
				    }
				});
			},
			addEmoji: function(emoji) {
				this.danmuValue = this.danmuValue+emoji;
			},
			closeShowModer: function() {
				const that=this;
				that.showEmoji = false;
		        that.$refs.myMsgDtail.$el.style.paddingBottom='20%';
			},
			//隐藏键盘
			hideKey: function() {
				uni.hideKeyboard()
				this.inputFocus();
			},
			//ref
			getEl: function(el) {
				if (typeof el === 'string' || typeof el === 'number') return el;
				if (WXEnvironment) {
					return el.ref;
				} else {
					return el instanceof HTMLElement ? el : el.$el;
				}
			},
			inputFocus: function() {
				var that = this;
				
				// #ifndef  APP-NVUE
				 
				
				if (this.showMore) {
					let move = uni.upx2px(180);
					var menuAnimation = uni.createAnimation({
						duration: 220,
						timingFunction: 'ease',
					})
					menuAnimation.translateX(0).step()
					 
					this.menuAnimationData = menuAnimation.export()
					this.showMore = false;
				}
				 
				// #endif
				
				
				// #ifdef  APP-NVUE
				if (this.showEmoji) {
					let main_chatDetail = this.getEl(this.$refs.chatDetail);
					let main_foot = this.getEl(this.$refs.foot);
					uni.hideKeyboard()
					this.hideEmojiPanel();
				} else {
					this.adjustPosition = true;
				}
				if (this.showMore) {
					let moreMenu = this.getEl(this.$refs.moreMenu);
					animation.transition(moreMenu, {
						styles: {
							transform: 'translateX(0)',
						},
						duration: 400, //ms
						timingFunction: 'linear',
						needLayout: false,
						delay: 0 //ms
					}, function(res) {
						that.scrollBottom();
					})
					this.showMore = false;
				}
				// #endif
			},
			previewImage: function(tempFilePaths) {
				console.log(tempFilePaths);
				// 预览图片
				uni.previewImage({
				    urls: tempFilePaths,
				    longPressActions: {
				        itemList: ['发送给朋友', '保存图片', '收藏'],
				        success: function(data) {
				            console.log('选中了第' + (data.tapIndex + 1) + '个按钮,第' + (data.index + 1) + '张图片');
				        },
				        fail: function(err) {
				            console.log(err.errMsg);
				        }
				    }
				});
			},
			dateFormat: function(timestamp, format) {
				return dateFormat(timestamp, format);
			},
			sub: function(val, start = 0, end = 10) {
				return sub(val,start,end);
			},
			splitGetStr: function(str,separator,index) {
				return splitGetStr(str,separator,index);
			},
			//文件保存到本地
			downloadFile: function(url) {
				console.log(url);
				//down(url);
				window.location.href=config.domain+"/common/download/resource?resource="+url
				return;
				const downloadTask = uni.downloadFile({
				    url: url, //仅为示例，并非真实的资源
				    success: (data) => {
				        if (data.statusCode === 200) {
							uni.showToast({
								title: '下载成功',
								icon: "none"
							}); 
							//文件保存到本地
							 uni.saveFile({
							     tempFilePath: data.tempFilePath, //临时路径
							     success: function(res) {
							         uni.showToast({
							             icon: 'none',
							             mask: true,
							             title: '文件已保存：' + res.savedFilePath, //保存路径
							             duration: 3000,
							         });
							         setTimeout(() => {
							             //打开文档查看
							             uni.openDocument({
							                 filePath: res.savedFilePath,
							                 success: function(res) {
							                     // console.log('打开文档成功');
							                 }
							             });
							         }, 3000)
							     }
							 });
				        }else{
							uni.showToast({
								title: '下载失败',
								icon: "none"
							}); 
						}
				    }
				});
				
				downloadTask.onProgressUpdate((res) => {
				    console.log('下载进度' + res.progress);
				    console.log('已经下载的数据长度' + res.totalBytesWritten);
				    console.log('预期需要下载的数据总长度' + res.totalBytesExpectedToWrite);
				
				    // 满足测试条件，取消下载任务。
				    // if (res.progress > 50) {
				    //     downloadTask.abort();
				    // }
				});
			},
			//保存图片到系统相册
			downLoadImage: function(url) {
				uni.downloadFile({
				    url: url,
				    success: (res) => {
				        if (res.statusCode === 200) {
				            //保存图片到系统相册
				            uni.saveImageToPhotosAlbum({
				                filePath: res.tempFilePath,
				                success: function() {
				                    uni.showToast({
				                        title: "保存成功",
				                        icon: "none"
				                    });
				                    return
				                },
				                fail: function() {
				                    uni.showToast({
				                        title: "保存失败，请稍后重试",
				                        icon: "none"
				                    });
				                    return
				                }
				            });
				        }
				    }
				})
			}
		},
		computed: {
			// ...mapState(['eventlist'])
			...mapState({
				eventlist: state => state.eventlist
			}),
			chatBodyBottom() {
				return `height:${this.srcollHeight}px;`
			},
			computedStyleContentVoiceLength() {
				return function(length) {
					let l = length / 1000;
					let w = 70;
					if (parseInt(l) < 2) {
						w = 70;
					} else if (parseInt(l) < 3) {
						w = 75;
					} else if (parseInt(l) < 5) {
						w = 85;
					} else if (parseInt(l) < 7) {
						w = 95;
					} else if (parseInt(l) < 10) {
						w = 110;
					} else if (parseInt(l) < 15) {
						w = 130;
					} else if (parseInt(l) < 20) {
						w = 150;
					} else if (parseInt(l) < 30) {
						w = 180;
					} else {
						w = 200;
					}
					w = w * 2;
					return 'width:' + w + 'rpx;position: relative;align-items: center;';
				}
			}
		}
	}
</script>

<style>
		@import "./CustomerServiceChat.css";
</style>
