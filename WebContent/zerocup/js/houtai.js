/**
 * Created by gxy on 2016/12/6.
 */

var  n = 1;
var user;
var userxtx;

window.onload=function(){

    ajax({
        url: "individual?user=" + getCookieValue("username"),
        async: true,
        data: null,
        method: 'get',
        success: function (data) {
            var o = JSON.parse(data);
            console.log(data);
            console.log(o.realName);
            user=o.realName;
            userxtx=o.smallpic;
            document.getElementById("big_touxiang").src = o.headpic;
            document.getElementById("yonghuid").innerHTML = o.realName;
            document.getElementById("user").innerHTML = o.realName;
            document.getElementById("user1").innerHTML = o.realName;
            document.getElementById("user_name").innerHTML = o.selfintro;
            document.getElementById("gonggao").innerHTML = o.selfintro;
            document.getElementById("rizhi_num").innerHTML = o.diaryNum;
            document.getElementById("zuji_num").innerHTML = o.trailNum;
            document.getElementById("zhuceriqi").innerHTML = o.regtime;
            document.getElementById("juzhudi").innerHTML = o.address;
            document.getElementById("biaoqian").innerHTML = o.description;
            document.getElementById("yonghushengri").innerHTML = o.birthday;
            document.getElementById("small_touxiang").src = o.smallpic;
            document.getElementById("small_touxiang1").src = o.smallpic;
            document.getElementById("xtx").src = o.smallpic;

        }
    });

    ajax({
        url: "diary?user=" + getCookieValue("username"),
        async: true,
        data: null,
        method: 'get',
            success: function (data) {
                var o = JSON.parse(data);
                console.log(data);
                console.log(o.data.length);
                var k = o.data.length;


                var zli = document.getElementById("zdiary");
                zli.innerHTML =o.data[0].content;

                var zli = document.getElementById("zdiarytime");
                zli.innerHTML =o.data[0].createtime;

                var zli = document.getElementById("zdiaryphoto");
                zli.src =o.data[0].diaryphoto;

                var zli = document.getElementById("zdiarytitle1");
                zli.innerHTML =o.data[0].title;

                var zli = document.getElementById("zdiary1");
                zli.innerHTML =o.data[0].content;

                var ul = document.getElementById("diarylist");
                for (n = 1; n <= k; n++) {
                    li = document.createElement("li");
                    li.setAttribute('class','diarylist12');
                    li.innerHTML =o.data[n - 1].title;
                    ul.appendChild(li);
                    var div = document.getElementById("drz");
                    var div2 = document.createElement("div");
                    div2.setAttribute('class', 'mydiary2');
                    div2.setAttribute('style', 'margin-top: 31px;');
                    var ul2 = document.createElement("ul");

                    var li2 = document.createElement("li");
                    li2.setAttribute('class','mydiary21');
                    var img2 = document.createElement("img");
                    img2.src = o.data[n - 1].diaryphoto;
                    li2.appendChild(img2);
                    ul2.appendChild(li2);

                    var li2 = document.createElement("li");
                    li2.setAttribute('class', 'mydiary22');
                    li2.innerHTML = o.data[n - 1].title;
                    ul2.appendChild(li2);

                    var li2 = document.createElement("li");
                    li2.setAttribute('class', 'mydiary23');
                    li2.innerHTML = o.data[n - 1].content;
                    ul2.appendChild(li2);

                    var li2 = document.createElement("li");
                    li2.setAttribute('class', 'mydiary24');
                    li2.innerHTML = o.data[n - 1].createtime;
                    li2.setAttribute('style', 'margin-left: 30px;');
                    ul2.appendChild(li2);

                    var li2 = document.createElement("li");
                    li2.setAttribute('class', 'mydiary24');
                    li2.innerHTML = '13&nbsp;&nbsp;喜欢&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 评论';
                    li2.setAttribute('style', 'float: right;');
                    ul2.appendChild(li2);

                    div2.appendChild(ul2);
                    div.appendChild(div2);

                }
            }



    });

    ajax({
        url: "photo?user=" + getCookieValue("username"),
        async: true,
        data: null,
        method: 'get',
        success: function (data) {
            var o = JSON.parse(data);
            console.log(data);
            console.log(o.data.length);

            var k = o.data.length;


            var zul = document.getElementById("zphoto");
                for (n = 1; n <= k; n++) {
                    if(o.data[n-1].path1) {
                    var li = document.createElement("li");
                    li.setAttribute('class', 'recentphoto21');
                    li.setAttribute('onclick', 'photowallblock');
                    var img = document.createElement("img");
                    img.src = o.data[n - 1].path1;

                    li.appendChild(img);
                    zul.appendChild(li);
                }
            }
            var zli = document.createElement("li");
            zli.setAttribute('class', 'recentphoto3');
            zli.setAttribute('onclick', 'photowallblock');
            zli.innerHTML ='查看更多 》';
            zul.appendChild(li);


            var zli = document.getElementById("zphototime");
            zli.innerHTML =o.data[0].createtime;


            var zli = document.getElementById("zphototitle");
            zli.innerHTML =o.data[0].titlelist;

             var zul = document.getElementById("zphoto1");

             for (n = 1; n <= k; n++) {
                 if(o.data[n-1].path1){
             var li = document.createElement("li");
             li.setAttribute('class', 'personalrecentphoto3');
             li.setAttribute('onclick', 'photowallblock');
             li.setAttribute('style', 'margin-left: 20px;');
             var img = document.createElement("img");
             img.src = o.data[n - 1].path1;
             li.appendChild(img);
             zul.appendChild(li);

             }
             }

            var m = 0;
            var ul = document.getElementById("photolist");
            while(o.data[m].title) {
                li = document.createElement("li");
                li.setAttribute('class','photolist2');
                li.innerHTML =eval('o.data['+m+'].titlelist');
                ul.appendChild(li);
                var div = document.getElementById("dxc");
                var div2 = document.createElement("div");
                div2.setAttribute('class', 'myphoto2');
                var ul2 = document.createElement("ul");
                var li2 = document.createElement("li");
                li2.setAttribute('class', 'myphoto21');
                li2.innerHTML = eval('o.data['+m+'].title');
                ul2.appendChild(li2);
                m++;
                for (n = 0; n < k; n++) {
                    var path = eval('o.data['+n+'].path'+m);
                    console.log(n);
                    console.log(path);
                    if (path) {
                        var li2 = document.createElement("li");
                        li2.setAttribute('class', 'myphoto22');
                        var img = document.createElement("img");
                        img.src = path;
                        li2.appendChild(img);
                        ul2.appendChild(li2);
                    }
                }
                div2.appendChild(ul2);
                div.appendChild(div2);

            }
        }
    });

    ajax({
        url: "footmark?user=" + getCookieValue("username"),
        async: true,
        data: null,
        method: 'get',
        success: function (data) {
            var o = JSON.parse(data);
            var k = o.data.length;
            console.log(data);
            document.getElementById("fmNum").innerHTML = k;
            for(n=1;n<=k;n++){
                var ul = document.getElementById("footmark");

                var li = document.createElement("li");
                var img = document.createElement("img");

                img.setAttribute("id", 'fm_' + n );
                img.src = o.data[n-1].footmark;

                li.appendChild(img);
                ul.appendChild(li);
            }

        }
    });
};



function pl(){
    var time = CurentTime();
    var k=n-1;
    var lou=k+'楼';

    /*	ajax({
     url: "pinglun?comment="+document.getElementById("pl").value+"&time="+time,
     dataType: "json",
     async: true,
     data: null,
     method:'post',
     success: function(data) {
     var o = JSON.parse(data);

     console.log(data);
     /!*console.log(o.headpic);
     document.getElementById("big_touxiang").src=o.headpic;
     document.getElementById("user_name").innerHTML=o.selfintro;*!/

     }
     });                                   评论导入数据库*/
    document.getElementById('pl_'+k).innerHTML=user+"&nbsp;于"+time+"发表评论<br><br>"+document.getElementById("pl").value;
    document.getElementById('pltx_'+k).innerHTML="<div class='pinglunbantouxiang'>" +
        "<img id='pinglun1_touxiang' ></div>";
    document.getElementById('pinglun1_touxiang').src=userxtx;

}

function addOne() {
    if (document.getElementById("pl").value == "") {
        alert('为什么一句话都不说，主人您这是在调戏自己么？');
    } else {
        var tbody = document.getElementById('table').lastChild;
        var tr = document.createElement('tr');


        td = document.createElement("td");
        td.innerHTML = "";
        td.setAttribute("id", 'pltx_' + n);
        td.setAttribute("width", "80px");
        td.setAttribute("height", "100px");
        tr.appendChild(td);


        td = document.createElement("td");
        td.innerHTML = "";
        td.setAttribute("id", 'pl_' + n);
        td.setAttribute("width", "1000px");
        td.setAttribute("height", "100px");
        tr.appendChild(td);
        n++;


        tbody.appendChild(tr);
        alert("主人，您的评论已生效!");
        pl();fenye(1,3);
    }
}

function deleteRow(r) {
    var i=r.parentNode.parentNode.rowIndex;
    document.getElementById('table').deleteRow(i);
}

function getCookieValue(c_name) {
    if (document.cookie.length > 0) {
        var tmp_cookie = document.cookie;

        tmp_c1 = (tmp_cookie.indexOf(" " + c_name + "=") > 0) ? (tmp_cookie.indexOf(" " + c_name + "=") + 1) : 0,
            tmp_c2 = (tmp_cookie.indexOf(";" + c_name + "=") > 0) ? (tmp_cookie.indexOf(";" + c_name + "=") + 1) : 0,
            tmp_c3 = (tmp_cookie.indexOf(c_name + "=") == 0) ? 0 : -1,
            c_start = tmp_c1 || tmp_c2 || tmp_c3;
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1;
            var c_end = tmp_cookie.indexOf(";", c_start);
            if (c_end == -1) c_end = tmp_cookie.length;
            return (tmp_cookie.substring(c_start, c_end));
        }
    }
    return "";
}

function CurentTime() {
    var now = new Date();

    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日

    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var ss = now.getSeconds();           //秒

    var clock = year + "-";

    if (month < 10)
        clock += "0";

    clock += month + "-";

    if (day < 10)
        clock += "0";

    clock += day + " ";

    if (hh < 10)
        clock += "0";

    clock += hh + ":";
    if (mm < 10) clock += '0';
    clock += mm + ":";

    if (ss < 10) clock += '0';
    clock += ss;
    return (clock);
}

