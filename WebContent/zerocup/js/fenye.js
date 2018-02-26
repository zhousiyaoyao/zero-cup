/**
 * Created by gxy on 2016/12/5.
 */

function fenye(dqy,hnum){
    var itable = document.getElementById("table");
    var num = itable.rows.length;//表格所有行数(所有记录数)
    console.log(num);
    var totalPage = 0;//总页数
    var pageSize = hnum;//每页显示行数
    //总共分几页
    if(num/pageSize > parseInt(num/pageSize)){
        totalPage=parseInt(num/pageSize)+1;
    }else{
        totalPage=parseInt(num/pageSize);
    }
    var currentPage = dqy;//当前页数
    var startRow = (currentPage - 1) * pageSize+1;//开始显示的行
    var endRow = currentPage * pageSize;//结束显示的行
    endRow = (endRow > num)? num : endRow;
    console.log(endRow);
    //遍历显示数据实现分页
    for(var i=1;i<(num+1);i++){
        var irow = itable.rows[i-1];
        if(i>=startRow && i<=endRow){
            irow.style.display = "block";
        }else{
            irow.style.display = "none";
        }
    }
    var pageEnd = document.getElementById("pageEnd");
    var tempStr = "共"+num+"条记录 分"+totalPage+"页 当前第"+currentPage+"页";
    if(currentPage>1){
        tempStr += "<a href=\"#\" onClick=\"fenye("+(1)+","+hnum+")\">首页</a>";
        tempStr += "<a href=\"#\" onClick=\"fenye("+(currentPage-1)+","+hnum+")\"><上一页</a>"
    }else{
        tempStr += "首页";
        tempStr += "<上一页";
    }

    if(currentPage<totalPage){
        tempStr += "<a href=\"#\" onClick=\"fenye("+(currentPage+1)+","+hnum+")\">下一页></a>";
        tempStr += "<a href=\"#\" onClick=\"fenye("+(totalPage)+","+hnum+")\">尾页</a>";
    }else{
        tempStr += "下一页>";
        tempStr += "尾页";
    }
    document.getElementById("barcon").innerHTML = tempStr;

}