var http = require("./http.js");
// 下载二进制文件
var down = function(url) {
	var params = {
		url: "/common/download/resource?resource="+url,
		method: "get",
		needToken: true,
		responseType: "arraybuffer",
		data: {},
		callBack: function(rest) {
			console.log(rest)
            const cd = rest.header['Content-disposition'];
			console.log(cd);
                filename = cd.match(/"(\S*)"/)[1]
                //获取全局唯一的文件管理器
                const fs = wx.getFileSystemManager()
                //指定临时文件存入的路径，后面字符串自定义
                const filePath = `${uni.env.USER_DATA_PATH}/${filename}`
                // 写文件
                fs.writeFile({
                    filePath,
                    data: rest.data,
                    encoding: "binary", //二进制流文件必须是 binary
                    success: (res) => {
                        this.handleFile(filePath)
                        console.log(111, 'down', res, filePath)
                    }
                });
		}
	};
	http.request(params);
};
// 下载普通文件
var down1 = function() {
    uni.downloadFile({
        url,
        success: (res) => {
            if (res.statusCode === 200) {
                this.handleFile(res.tempFilePath)
                console.log(222, 'down1', res)
            }
        }
    })
};
// 对不同文件的处理
var handleFile = function(filePath) {
    const filetype = filePath.split('.')[1],
        typeObj = {
            gif: 'img',
            GIF: 'img',
            png: 'img',
            PNG: 'img',
            jpg: 'img',
            JPG: 'img',
            jpeg: 'img',
            mp4: 'video',
            doc: 'doc',
            docx: 'doc',
            xls: 'doc',
            xlsx: 'doc',
            ppt: 'doc',
            pptx: 'doc',
            pdf: 'doc'
        },
        result = (ok = '成功保存到相册', no = '保存失败') => {
            return {
                filePath,
                success: res => {
                    uni.showToast({
                        title: ok
                    })
                },
                fail: err => {
                    uni.showToast({
                        title: no
                    })
                }
            }
        }
    if (typeObj[filetype] === 'video') {
        // 保存视频到系统相册:mp4
        uni.saveVideoToPhotosAlbum(result())
    } else if (typeObj[filetype] === 'img') {
        // 保存图片到系统相册:gif,jpg,jpeg,png,GIF,JPG,PNG
        uni.saveImageToPhotosAlbum(result())
    } else {
        // 打开文件:doc,docx,xls,xlsx,ppt,pptx,pdf
        uni.openDocument({
            ...result('打开文档成功', '打开文档失败'),
            showMenu: true//showMenu是否显示右上角菜单
        });
    }
}

  
module.exports = {
	down: down
}