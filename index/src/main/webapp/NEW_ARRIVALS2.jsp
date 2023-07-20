<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
    區域標籤高度(height)，百分比要生效，需要同時設定<html>與<body>的高度(height)設為100%
        <html lang="zh" style="height: 100%;">
        <body style="background-color: lightgray;margin: 0;height: 100%;">
-->
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>The official Chia.Q online store</title>
    <link rel="stylesheet" href="style.css" href="">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <style>
    html, body {
        height: 100%;
        width: 100%;
        margin: 0;
    }
    </style>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/animejs/2.0.2/anime.min.js"></script>
</head>
<body class="body" style="text-align: center;">
    <!-- 登入帳號密碼後自動 擷取mysql 當前會員資料 顯示會員姓名 開始-->
    <h2 class='container' style='margin-bottom:1%;'>${result.memberName} 歡迎光臨 </h2>
    <!-- 結束-->
    <div class="header">
        <!-- <h2 class="logo"><a href="#">Chia.Q_select</a></h2> -->
        <h1 class="ml5">
            <span class="text-wrapper">
                <a href="index2.jsp" class="index" ;style="text-decoration:none;">
                <span class="line line1"></span>
                <span class="letters letters-left">Chia.Q</span>
                <span class="letters ampersand">&amp;</span>
                <span class="letters letters-right">select</span>
                <span class="line line2"></span>
                </a>
            </span>
          </h1>
            <script>anime.timeline({loop: true})
                .add({
                targets: '.ml5 .line',
                opacity: [0.5,1],
                scaleX: [0, 1],
                easing: "easeInOutExpo",
                duration: 700
                }).add({
                targets: '.ml5 .line',
                duration: 600,
                easing: "easeOutExpo",
                translateY: (el, i) => (-0.625 + 0.625*2*i) + "em"
                }).add({
                targets: '.ml5 .ampersand',
                opacity: [0,1],
                scaleY: [0.5, 1],
                easing: "easeOutExpo",
                duration: 600,
                offset: '-=600'
                }).add({
                targets: '.ml5 .letters-left',
                opacity: [0,1],
                translateX: ["0.5em", 0],
                easing: "easeOutExpo",
                duration: 600,
                offset: '-=300'
                }).add({
                targets: '.ml5 .letters-right',
                opacity: [0,1],
                translateX: ["-0.5em", 0],
                easing: "easeOutExpo",
                duration: 600,
                offset: '-=600'
                }).add({
                targets: '.ml5',
                opacity: 0,
                duration: 1000,
                easing: "easeOutExpo",
                delay: 1000
                });
            </script>

         <div class="menu" >
            <div class="icon">
                <a href="#" id="shoppingButton" class="shopping"><img src="img/shopping-cart.png" title="購物車"></a>
                <a href="#" class="Login-popo"><img src="img/user.png" href="#" title="會員資料"></a>
            </div>
            <section class="section">
                <div class="wrapper">
                    <span class="icon-close">
                        <i class='bx bx-x' ></i>
                    </span>
                    <div class="logreg-box">
                        <!-- login form -->
                        <div class="form-box login">
                            <div class="loreg-title">
							<!-- 從mysql連結登入會員其他資料 開始-->
	                            <h1 class='container' style="font-size: 32px;margin: 8px;">會員資料</h1>
	        					<h3 class='menberX'>姓名：${result.memberName}</h3>
	                            <h3 class='menberX'>信箱：${result.account}</h3>
	                            <h3 class='menberX'>手機：${result.tel}</h3>
	                            <h3 class='menberX'>手機：${result.address}</h3>
                            <!-- 結束-->
                                <button type="submit"
                                class="registerLink" style="display:none;">註冊會員</button>
                                 <form action="./logout">
                            <button type="submit"
                                class="registerLink">會員登出</button>
                                 </form>
                          </div>
                        </div>
                        
                        <!-- register from -->
                        <div class="form-box register">
                            <div class="loreg-title">
                                <h2>會員註冊</h2>
                                <p>請提供會員相關資料</p>
                            </div>
                             <form id="myform" action="./join" method="POST">
                                <div class="input-box">
                                    <span class="icon"><i class='bx bxs-user'></i></span>
                                    <input type="text" name="name" id="name" pattern="[^/\\\.\?\*\$\+,;\^!@#%]*"
									title="禁用特殊字元[^.\?*$+,;!@#%]"  required>
                                    <label>Full Name</label>
                                </div>
                                
                                <div class="input-box">
                                    <span class="icon"><i class='bx bxs-envelope'></i></span>
                                  <input type="email" name="account" id="acc" required>
                                    <label>Email</label>
                                </div>

                                <div class="input-box">
                                    <span class="icon"><i class='bx bxs-lock-alt'></i></span>
                                    <input type="password" name="password" id="pass" required>
                                    <label>Password</label>
                                </div>
                                <div class="input-box">
                                    <span class="icon"><i class='bx bx-phone'></i></span>
                                    <input type="text" name="tel" id="tel" pattern="09\d{8}" required>
                                    <label>Tel</label>
                                </div>
                                <div class="input-box">
                                    <span class="icon"><i class='bx bx-home'></i></span>
                                    <input type="text" name="address" id="addr" required >
                                    <label>address</label>
                                </div>
                                
                                <div class="remember-forgot">
                                    <label><input type="checkbox">
                                    我接受使用條款及銷售條款</label>
                                </div>

                                <button type="submit"
                                class="btn">註冊</button>

                                <div class="logerg-link">
                                    <p>已經有會員 ? <a href="#"
                                    class="login-link">Login</a></p>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
            <script src="script.js"></script>
            <ul class="drop-down-menu" >
                <li><a href="NEW_ARRIVALS2.jsp">NEW ARRIVALS</a></li>
                    <li><a href="#">MEN</a>
                        <ul>
                            <li><a href="#">TOP</a></li>
                            <li><a href="#">BOTTOM</a></li>
                            <li><a href="#">ACCESSORIES</a></li>    
                            <li><a href="#">Coats + Jackets</a></li>
                            <li><a href="#">Underwear</a></li>      
                        </ul>
                    </li>
                    <li><a href="#">WOMEN</a>
                        <ul>
                            <li><a href="#">TOP</a></li>
                            <li><a href="#">BOTTOM</a></li>
                            <li><a href="#">ACCESSORIES</a></li>    
                            <li><a href="#">Coats + Jackets</a></li> 
                            <li><a href="#">Underwear</a></li>     
                        </ul>
                    </li>
                <li><a href="#">HEADWEAR</a></li>
                <li><a href="#">HOME & LIFESTYLE</a></li>
            </ul>
        </div>    
    </div>
    <div class="main">
    <article style="height: 80%;width: 90%;margin: auto;clear: both;background-color: gray;">
        <div class="card-list">
            <a href="bag2.jsp"><img src="img/bag.jpg" alt="bag" class="goods"></a>
            <h1>bag</h1>
            <p class="price">NT$999</p>
        </div>        
        <div class="card-list">
            <a href="Glasses2.jsp"><img src="img/Glasses.jpg" alt="Glasses" class="goods"></a>
            <h1>Glasses</h1>
            <p class="price">NT$799</p>
        </div>
        <div class="card-list">
            <a href="Rings2.jsp"><img src="img/Rings.jpg" alt="Rings" class="goods"></a>
            <h1>Rings</h1>
            <p class="price">NT$1999</p>
        </div>
        <div class="card-list">
            <a href="Shirt_2.jsp"><img src="img/shirt.jpg" alt="shirt" class="goods"></a>
            <h1>Shirt</h1>
            <p class="price">NT$1280</p>
        </div>        
        <div class="card-list">
            <a href="Shirt2_2.jsp"><img src="img/shirt2.jpg" alt="shirt2" class="goods"></a>
            <h1>Shirt</h1>
            <p class="price">NT$799</p>
        </div>
        <div class="card-list">
            <a href="Pants2.jsp"><img src="img/Pants.jpg" alt="Pants" class="goods"></a>
            <h1>Pants</h1>
            <p class="price">NT$1599</p>
        </div>
        <div style="display: none;">
            <div class="addCard">
                <button type="button" class="buybutton" data-id="0">加入購物車</button>
            </div>
            <div class="gobuy">
                <button type="button"  class="buybutton">直接購買</button>
            </div>
        </div>
        <!--購物車-->
        <div>
            <div class="card">
                <h1>購物車</h1>
                <div class="cart-list-container">
                    <ul class="listCard"></ul>
                </div>
                <div class="checkout">
                    <div class="gotobuy"><a href="shoppingPage2.jsp"><span>直接購買</span></a></div>
                    <div class="closeshopping"><span>close</span></div>
                </div>
            </div>
        </div>
        <script src="app1_new.js"></script>
    </article>
    </div>
    <div>
    <footer style="height:35%;width: 90%;margin: auto;clear: both;">
        <div class="container">
            <div class="row">
              <div class="col-md-4">
                <h4>About Us</h4>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer feugiat tempus nisi, id feugiat magna bibendum vel.</p>
              </div>
              <div class="icons">
                <h4>Follow Us</h4>
                <a href="#"><ion-icon name="logo-facebook"></ion-icon></a>
                <a href="#"><ion-icon name="logo-instagram"></ion-icon></a>
                <a href="#"><ion-icon name="logo-youtube"></ion-icon></a>
            </div>
            <script src="https://unpkg.com/ionicons@5.4.0/dist/ionicons.js"></script>
              <div class="col-md-4">
                <h4>Contact Us</h4>
                <ul class="contact-info">
                  <li><i class="fas fa-map-marker-alt"></i> 123 Main Street, New York, NY 10001</li>
                  <li><i class="fas fa-envelope"></i><a href="mailto:ChiaQ_select@gmail.com">ChiaQ_select@gmail.com</a></li>
                  <li><i class="fas fa-phone"></i> (03) 123-4567</li>
                </ul>
              </div>
            </div>
          </div>
    </footer>
    </div>
    
</body>
</html>