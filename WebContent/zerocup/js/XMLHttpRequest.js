/**
 * Created by gxy on 2016/11/26.
 */


function ajax(all){
    // all =  {url:'',method:'',data:'',success:'',async:''}
    //默认参数
        all.url = all.url || '';
        all.method = all.method || 'get';
        all.data = all.data || '';
        all.success = all.success || '';
        all.async = all.async || true;
    //get请求-拼接url
    if(all.method.toLowerCase() == 'get'){
        if(typeof all.data == 'object'){
            all.data = [];
            for (var k in all.data){
                all.data.push(k+'='+all.data[k]);
                all.data.join('&');
            }
        }
       // all.url += (all.url.indexOf('?' == -1) ? '?' : '') + all.data
        all.url += all.data;
    }
    //post请求-转换字符串
    if(all.method.toLowerCase() == 'post'){
        if(typeof all.data == 'object'){
            var arrs = [];
            for (var k in all.data){
                arrs.push(k+'='+all.data[k]);
            }
            all.data = arrs.join('&');
        }
    }
    //创建发送请求
    var xhr = window.XMLHttpRequest ?  new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP'); //兼容ie
    xhr.open(all.method,all.url,all.async);
    if(all.method == 'post'){
        xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
        xhr.send(all.data);
    }else{
        xhr.send(null);
    }
    //异步请求
    if(all.async == true){
        xhr.onreadystatechange = function(){
            if(xhr.readyState == 4){
                judge();
            }
        }
    }
    // xhr.abort(); // 取消异步请求
    //同步请求
    if(all.async == false){
        judge();
    }
    //返回状态判断
    function judge(){
        if(xhr.status == 200){
            all.success(xhr.responseText);
        }else{
            all.success('error:' + xhr.status + xhr.statusText);
        }
    }
}