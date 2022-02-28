const formatTime = date => {
  const year = date.getFullYear();
  const month = date.getMonth() + 1;
  const day = date.getDate();
  const hour = date.getHours();
  const minute = date.getMinutes();
  const second = date.getSeconds();
  return [year, month, day].map(formatNumber).join('-') + ' ' + [hour, minute, second].map(formatNumber).join(':');
};

const formatNumber = n => {
  n = n.toString();
  return n[1] ? n : '0' + n;
};

const formatHtml = content => {
  content = content.replace(/\<img/gi, '<img style="width:100% !important;height:auto !important;margin:0;display:flex;" ');
  content = content.replace(/\<td/gi, '<td  cellspacing="0" cellpadding="0" border="0" style="display:block;vertical-align:top;margin: 0px; padding: 0px; border: 0px;outline-width:0px;" ');
  content = content.replace(/width=/gi, 'sss=');
  content = content.replace(/height=/gi, 'sss=');
  content = content.replace(/ \/\>/gi, ' style="max-width:100% !important;height:auto !important;margin:0;display:block;" \/\>');
  return content;
};

/**
 * 获取链接上的参数
 */
const getUrlKey = (name) => {
	return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || ['', ''])[1]
		.replace(/\+/g, '%20')) || null
}

/**
 * 移除购物车Tabbar的数字
 */
const removeTabBadge = () => {
	let pl = ''
	// #ifdef MP-WEIXIN
	pl = 'mp'
	// #endif
	uni.removeTabBarBadge({
		index: pl=='mp' ? 3 : 2
	})
}
  var regYear = getRegExp("(y+)", "i");
  var dateFormat = function (timestamp, format) {
    if (!format) {
      format = "yyyy-MM-dd hh:mm:ss";
    }
    timestamp = parseInt(timestamp);
    var realDate = getDate(timestamp);
  
    function timeFormat(num) {
      return num < 10 ? '0' + num : num;
    }
    var date = [
      ["M+", timeFormat(realDate.getMonth() + 1)],
      ["d+", timeFormat(realDate.getDate())],
      ["h+", timeFormat(realDate.getHours())],
      ["m+", timeFormat(realDate.getMinutes())],
      ["s+", timeFormat(realDate.getSeconds())],
      ["q+", Math.floor((realDate.getMonth() + 3) / 3)],
      ["S+", realDate.getMilliseconds()],
    ];
    var reg1 = regYear.exec(format);
    // console.log(reg1[0]);
    if (reg1) {
  
      format = format.replace(reg1[1], (realDate.getFullYear() + '').substring(4 - reg1[1].length));
    }
    for (var i = 0; i < date.length; i++) {
      var k = date[i][0];
      var v = date[i][1];
  
      var reg2 = getRegExp("(" + k + ")").exec(format);
      if (reg2) {
        format = format.replace(reg2[1], reg2[1].length == 1 ?
          v : ("00" + v).substring(("" + v).length));
      }
    }
    return format;
  }
  
  var sub = function (val, start = 0, end = 10) {
    if (val.length == 0 || val == undefined) {
      return;
    }
    if (val.length > end) {
      return val.substring(start, end) + "...";
    } else {
      return val;
    }
  }
  var splitGetStr = function(str,separator,index) {
	  var strs = str.split(separator);
	  return strs[strs.length-index];
  }
  
module.exports = {
  formatTime: formatTime,
	getUrlKey: getUrlKey,
  formatHtml: formatHtml,
	removeTabBadge: removeTabBadge,
	dateFormat: dateFormat,
	sub:sub,
	splitGetStr: splitGetStr
};