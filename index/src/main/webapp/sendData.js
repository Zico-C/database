// 獲取按鈕元素
var button = document.getElementById('checkoutButton');
var form = document.getElementById('myForm');

// 添加監聽點擊事件
button.addEventListener('click', function(event) 
{
  // 彈出確認視窗
  var result = confirm('確定送出此訂單？');

  // 根據用戶出現相應操作
  if (result) {
    sendDataToBackend();


    localStorage.clear();
  } else {
    event.preventDefault(); // 阻止表單默認提交
  }
});



// 定義一個函數用於更新和遍歷存儲的值
function processOrders() 
{
  var storedValue = localStorage.getItem("cartItems");

// 檢查存儲的值是否存在
  if (storedValue) {
    var orders = JSON.parse(storedValue);

    
// 遍歷每個訂單並輸出相應的值
    orders.forEach(function(order, index) {
      console.log("Order: " + (index + 1));
      console.log("ProductName: " + order.productName);
      console.log("Quantity: " + order.quantity);
      console.log("Price: " + order.price);
      console.log("Total Price: " + order.totalPrice);
      console.log("");
    });
  }
}



// 定義一個函數用於將參數發送到後端
function sendDataToBackend() 
{
  var storedValue = localStorage.getItem("cartItems");
  
 // 檢查存儲的值是否存在
  if (storedValue) {
    var orders = JSON.parse(storedValue);

    // 創建一個 URLSearchParams 對象
    var params = new URLSearchParams();

    // 遍歷每個訂單並將參數添加到 URLSearchParams 對像中
    orders.forEach(function(order, index) 
    {
      params.append("order[" + index + "][order]", (index + 1));
  	  params.append("order[" + index + "][productName]", order.productName);
      params.append("order[" + index + "][quantity]", order.quantity);
      params.append("order[" + index + "][price]", order.price);
      params.append("order[" + index + "][totalPrice]", order.totalPrice);
    });

    // 創建XMLHttpRequest對象
    var xhr = new XMLHttpRequest();
    
    var pickUpEl = document.getElementById("pickUp").value;
    var addressEl = document.getElementById("address").value;
    var payMentEl = document.getElementById("payMent").value;
    var accountEl = document.getElementById("account1").value;
    // 設置請求方法和URL
    xhr.open("POST", "CheckServlet2?pickUp="+pickUpEl+"&address="+addressEl+"&payMent="+payMentEl+"&account1="+accountEl, true);
    
    
    // 監聽請求狀態變化
    xhr.onreadystatechange = function() 
    {
      if (xhr.readyState === 4) 
      {
        if (xhr.status === 200) 
        {
          // 請求成功
          var response = xhr.responseText;
          // 調用函數以處理初始存儲的值
		  processOrders();
          if(response.length>0)
          {
              alert("訂單送出成功！將跳轉回首頁...");
              location.href="index.jsp"
           }
          console.log("服務器響應: " + response);
        } else {
          // 请求失败
          console.log("請求失敗");
        }
      }
    };
   // 設置請求頭的內容類型為 application/x-www-form-urlencoded
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // 發送請求
    xhr.send(params.toString());
  }
}



// 當存儲的值發生變化時調用函數進行更新和遍歷
window.addEventListener("storage", function(event) 
{
  if (event.key === "cartItems") 
  {
    processOrders();
  }
});


