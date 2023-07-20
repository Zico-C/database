const openShopping = document.querySelector('.shopping');
const closeShopping = document.querySelector('.closeshopping');
let list = document.querySelector('.list');

const body = document.querySelector('body')
const total = document.querySelector('.total');
const addCard = document.querySelector('.addCard');
//獲取加入購物車按鈕
const addToCartButton = document.querySelector('.addCard');
//獲取購物車清單容器
const cartList  = document.querySelector('.listCard');
// 新增點擊事件 
addToCartButton.addEventListener('click', addToCart);
//以上OK


openShopping.addEventListener('click',()=>{
    body.classList.add('active');
})
addToCartButton.addEventListener('click',()=>{
    body.classList.add('active');
})
closeShopping.addEventListener('click',()=>{
    body.classList.remove('active');
})




function addToCart() {
  // 取得 數量 input 的值
  const quantityInput = document.querySelector('#quantity');
  const quantity = parseInt(quantityInput.value);

  // 獲取圖片的URL、價格
  const image = document.querySelector('.goods');
  const imageURL = image.getAttribute('src');
  const price = document.querySelector('h6').innerText;
  const ProductName = document.querySelector('#ProductName').innerText;
  // 計算總價
  const totalPrice = quantity * parseInt(price.substring(price.lastIndexOf(' ') + 1));

  // 建立購物車清單
  const cartItem = document.createElement('li');
  cartItem.innerHTML = `
    <div style ="display:flex;">
    <img src="${imageURL}" alt="item">
    <span>數量: ${quantity}</span>&nbsp;&nbsp;&nbsp;
    <span>價格: ${price}</span>&nbsp;&nbsp;&nbsp;
    <span>總價: <span class="totalPrice">${totalPrice}</span></span>
    <span style ="display:none;"> ${ProductName}</span>
    </div>
    <button class="increaseButton">+</button>
    <button class="decreaseButton">-</button>
    <button class="removeButton">删除</button>
  `;

  // 點選加入購物車到購物車清單
  cartList.appendChild(cartItem);

  // 保存購物車資訊到 localStorage
  saveCartItemToLocalStorage(quantity, imageURL, price, totalPrice,ProductName);
  
  // 新增點擊事件刪除按鈕
  const removeButton = cartItem.querySelector('.removeButton');
  removeButton.addEventListener('click', removeCartItem);

  // 新增點擊事件減少按鈕
  const decreaseButton = cartItem.querySelector('.decreaseButton');
  decreaseButton.addEventListener('click', decreaseQuantity);

  // 新增點擊事件新增按鈕
  const increaseButton = cartItem.querySelector('.increaseButton');
  increaseButton.addEventListener('click', increaseQuantity);

}

function removeCartItem(event) {
    const cartItem = event.target.parentElement;
    cartList.removeChild(cartItem);
    updateLocalStorage();


  }
  
  function decreaseQuantity(event) {
    const cartItem = event.target.parentElement;
    const quantitySpan = cartItem.querySelector('span:nth-child(2)');
    let quantity = parseInt(quantitySpan.innerText.substring(quantitySpan.innerText.lastIndexOf(' ') + 1));
    if (quantity > 1) {
        quantity--;
        quantitySpan.innerText = `數量: ${quantity}`;
        updateTotalPrice(cartItem, quantity);
        updateLocalStorage();

    }
  }
  
  function increaseQuantity(event) {
    const cartItem = event.target.parentElement;
    const quantitySpan = cartItem.querySelector('span:nth-child(2)');
    let quantity = parseInt(quantitySpan.innerText.substring(quantitySpan.innerText.lastIndexOf(' ') + 1));
    quantity++;
    quantitySpan.innerText = `數量: ${quantity}`;
    updateTotalPrice(cartItem, quantity);
    updateLocalStorage();

    }
  
    function updateTotalPrice(cartItem, quantity) {
      const priceSpan = cartItem.querySelector('span:nth-child(3)');
      const price = parseInt(priceSpan.innerText.substring(priceSpan.innerText.lastIndexOf(' ') + 1));
      const totalPriceSpan = cartItem.querySelector('.totalPrice');
      const totalPrice = quantity * price;
      totalPriceSpan.innerText = totalPrice;
    }


    function saveCartItemToLocalStorage(quantity, imageURL, price, totalPrice,productName) {
      // 先從 localStorage 中取得之前的購物車資訊
      const existingCartItems = JSON.parse(localStorage.getItem('cartItems')) || [];
    
      // 建立新的購物車項目物件
      const cartItem = {
        quantity: quantity,
        imageURL: imageURL,
        price: price,
        totalPrice: totalPrice,
        productName:productName
      };
    
      // 將新的購物車項目添加到購物車清單
      existingCartItems.push(cartItem);
    
      // 將更新後的購物車清單存儲到 localStorage
      localStorage.setItem('cartItems', JSON.stringify(existingCartItems));
    }

    window.addEventListener('DOMContentLoaded', initializeCart);

function initializeCart() {
  // 檢查 localStorage 中是否有購物車資訊
  const existingCartItems = JSON.parse(localStorage.getItem('cartItems')) || [];

  // 將購物車資訊呈現在畫面上
  for (const cartItem of existingCartItems) {
    const { quantity, imageURL, price, totalPrice,productName} = cartItem;

    const cartItemElement = document.createElement('li');
    cartItemElement.innerHTML = `
      <div style ="display:flex;">
        <img src="${imageURL}" alt="item">
        <span>數量: ${quantity}</span>&nbsp;&nbsp;&nbsp;
        <span>價格: ${price}</span>&nbsp;&nbsp;&nbsp;
        <span>總價: <span class="totalPrice">${totalPrice}</span></span>
        <span style ="display:none;"> ${productName}</span>
      </div>
      <button class="increaseButton1">+</button>
      <button class="decreaseButton1">-</button>
      <button class="removeButton1">删除</button>
    `;




    // 新增點擊事件刪除按鈕
    const removeButton1 = cartItemElement.querySelector('.removeButton1');
    removeButton1.addEventListener('click', removeCartItem);

    // 新增點擊事件減少按鈕
    const decreaseButton1 = cartItemElement.querySelector('.decreaseButton1');
    decreaseButton1.addEventListener('click', decreaseQuantity);

    // 新增點擊事件新增按鈕
    const increaseButton1 = cartItemElement.querySelector('.increaseButton1');
    increaseButton1.addEventListener('click', increaseQuantity);
    
    cartList.appendChild(cartItemElement);
  }      

}

function removeCartItem1(event) {
  const cartItem = event.target.parentElement;
  cartList.removeChild(cartItem);
  updateLocalStorage();


}

function decreaseQuantity1(event) {
  const cartItem = event.target.parentElement;
  const quantitySpan = cartItem.querySelector('span:nth-child(2)');
  let quantity = parseInt(quantitySpan.innerText.substring(quantitySpan.innerText.lastIndexOf(' ') + 1));
  if (quantity > 1) {
      quantity--;
      quantitySpan.innerText = `數量: ${quantity}`;
      updateTotalPrice(cartItem, quantity);
      updateLocalStorage();

  }
}

function increaseQuantity1(event) {
  const cartItem = event.target.parentElement;
  const quantitySpan = cartItem.querySelector('span:nth-child(2)');
  let quantity = parseInt(quantitySpan.innerText.substring(quantitySpan.innerText.lastIndexOf(' ') + 1));
  quantity++;
  quantitySpan.innerText = `數量: ${quantity}`;
  updateTotalPrice(cartItem, quantity);
  updateLocalStorage();
  }

  function updateLocalStorage() {
    const cartItems = [];
    const cartItemElements = document.querySelectorAll('.listCard li');
  
    // 將購物車項目資訊存入 cartItems 陣列
    for (const cartItemElement of cartItemElements) {
      const quantity = parseInt(cartItemElement.querySelector('span:nth-child(2)').innerText.split(':')[1].trim());
      const imageURL = cartItemElement.querySelector('img').getAttribute('src');
      const price = cartItemElement.querySelector('span:nth-child(3)').innerText.split(':')[1].trim();
      const totalPrice = parseInt(cartItemElement.querySelector('.totalPrice').innerText);
      const productName =cartItemElement.querySelector('span:nth-child(5)').innerText;
      cartItems.push({ quantity, imageURL, price, totalPrice ,productName});
    }
  
    // 將 cartItems 陣列轉換為 JSON 字串，並存入 localStorage
    localStorage.setItem('cartItems', JSON.stringify(cartItems));
  }
  
  
  
  