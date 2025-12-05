const API_URL = '/api';

let cart = [];
let products = [];

// Initialize
document.addEventListener('DOMContentLoaded', () => {
    loadCart();
    fetchProducts();
    fetchUserInfo();
});

// Fetch Current User
async function fetchUserInfo() {
    try {
        const response = await fetch('/api/orders/auth/me');
        if (response.ok) {
            const user = await response.json();
            const userInfoContainer = document.getElementById('user-info');
            if (userInfoContainer) {
                userInfoContainer.innerHTML = `
                    <a href="profile.html" style="text-decoration: none; color: var(--primary); font-weight: 600; display: flex; align-items: center; gap: 6px;">
                        <span>Hello, ${user.username}</span>
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path><circle cx="12" cy="7" r="4"></circle></svg>
                    </a>
                `;
            }
        }
    } catch (error) {
        console.error('Failed to fetch user info:', error);
    }
}

// Load Cart from LocalStorage
function loadCart() {
    const storedCart = localStorage.getItem('freshmarket_cart');
    if (storedCart) {
        cart = JSON.parse(storedCart);
        updateCartUI();
    }
}

// Save Cart to LocalStorage
function saveCart() {
    localStorage.setItem('freshmarket_cart', JSON.stringify(cart));
}

// Add Item to Cart
function addToCart(productId) {
    const product = products.find(p => p.id === productId);
    if (!product) return;

    const existingItem = cart.find(item => item.product.id === productId);
    if (existingItem) {
        existingItem.quantity += 1;
    } else {
        cart.push({ product, quantity: 1 });
    }

    saveCart();
    updateCartUI();
    openCart();
}

// Remove Item from Cart
function removeFromCart(productId) {
    cart = cart.filter(item => item.product.id !== productId);
    saveCart();
    updateCartUI();
}

// Update Cart Quantity
function updateQuantity(productId, change) {
    const item = cart.find(item => item.product.id === productId);
    if (item) {
        item.quantity += change;
        if (item.quantity <= 0) {
            removeFromCart(productId);
        } else {
            saveCart();
            updateCartUI();
        }
    }
}

// Checkout Logic - Redirect
function checkout() {
    if (cart.length === 0) {
        alert('Your cart is empty!');
        return;
    }
    saveCart();
    window.location.href = 'checkout.html';
}

// Fetch Products from Backend
async function fetchProducts() {
    try {
        const response = await fetch(`${API_URL}/products`);
        if (!response.ok) throw new Error('Failed to fetch products');
        products = await response.json();
        renderProducts(products);
    } catch (error) {
        console.error('Error:', error);
        document.getElementById('product-grid').innerHTML = '<div class="error">Failed to load products. Please check if the backend is running.</div>';
    }
}

// Filter Products
function filterProducts() {
    const query = document.getElementById('search-input').value.toLowerCase();
    const filtered = products.filter(p =>
        p.name.toLowerCase().includes(query) ||
        p.category.toLowerCase().includes(query)
    );
    renderProducts(filtered);
}

// Render Products to Grid
function renderProducts(productsToRender) {
    const grid = document.getElementById('product-grid');

    if (productsToRender.length === 0) {
        grid.innerHTML = '<div class="no-results" style="grid-column: 1/-1; text-align: center; color: var(--text-light); padding: 2rem;">No products found matching your search.</div>';
        return;
    }

    grid.innerHTML = productsToRender.map(product => `
        <div class="product-card">
            <img src="${product.imageUrl}" alt="${product.name}" class="product-image" onerror="this.onerror=null; this.src='data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSI2MDAiIGhlaWdodD0iNDAwIiB2aWV3Qm94PSIwIDAgNjAwIDQwMCI+PHJlY3Qgd2lkdGg9IjYwMCIgaGVpZ2h0PSI0MDAiIGZpbGw9IiNmM2Y0ZjYiLz48dGV4dCB4PSI1MCUiIHk9IjUwJSIgZG9taW5hbnQtYmFzZWxpbmU9Im1pZGRsZSIgdGV4dC1hbmNob3I9Im1pZGRsZSIgZm9udC1mYW1pbHk9InNhbnMtc2VyaWYiIGZvbnQtc2l6ZT0iMjQiIGZpbGw9IiM2YjcyODAiPkltYWdlIE5vdCBBdmFpbGFibGU8L3RleHQ+PC9zdmc+'">
            <div class="product-info">
                <div class="product-cat">${product.category}</div>
                <h3 class="product-name">${product.name}</h3>
                <p class="product-desc">${product.description}</p>
                <div class="product-footer">
                    <div class="product-price">₹${product.price.toFixed(2)}</div>
                    <button class="add-btn" onclick="addToCart(${product.id})">
                        Add to Cart
                    </button>
                </div>
            </div>
        </div>
    `).join('');
}

// Add Item to Cart
function addToCart(productId) {
    const product = products.find(p => p.id === productId);
    if (!product) return;

    const existingItem = cart.find(item => item.product.id === productId);
    if (existingItem) {
        existingItem.quantity += 1;
    } else {
        cart.push({ product, quantity: 1 });
    }

    updateCartUI();
    openCart();
}

// Remove Item from Cart
function removeFromCart(productId) {
    cart = cart.filter(item => item.product.id !== productId);
    updateCartUI();
}

// Update Cart Quantity
function updateQuantity(productId, change) {
    const item = cart.find(item => item.product.id === productId);
    if (item) {
        item.quantity += change;
        if (item.quantity <= 0) {
            removeFromCart(productId);
        } else {
            updateCartUI();
        }
    }
}

// Update Cart UI
function updateCartUI() {
    const cartCount = document.getElementById('cart-count');
    const cartItems = document.getElementById('cart-items');
    const cartTotal = document.getElementById('cart-total');

    // Update Count
    const totalItems = cart.reduce((sum, item) => sum + item.quantity, 0);
    cartCount.textContent = totalItems;

    // Render Items
    if (cart.length === 0) {
        cartItems.innerHTML = '<div class="empty-cart">Your cart is empty.</div>';
    } else {
        cartItems.innerHTML = cart.map(item => `
            <div class="cart-item">
                <img src="${item.product.imageUrl}" alt="${item.product.name}">
                <div class="cart-item-info">
                    <div class="cart-item-title">${item.product.name}</div>
                    <div class="cart-item-price">₹${item.product.price.toFixed(2)}</div>
                    <div class="cart-item-actions">
                        <button class="qty-btn" onclick="updateQuantity(${item.product.id}, -1)">-</button>
                        <span>${item.quantity}</span>
                        <button class="qty-btn" onclick="updateQuantity(${item.product.id}, 1)">+</button>
                    </div>
                </div>
            </div>
        `).join('');
    }

    // Update Total
    const total = cart.reduce((sum, item) => sum + (item.product.price * item.quantity), 0);
    cartTotal.textContent = `₹${total.toFixed(2)}`;
}

// Toggle Cart Sidebar
function toggleCart() {
    const sidebar = document.getElementById('cart-sidebar');
    const overlay = document.getElementById('overlay');
    sidebar.classList.toggle('open');
    overlay.classList.toggle('visible');
}

function openCart() {
    const sidebar = document.getElementById('cart-sidebar');
    const overlay = document.getElementById('overlay');
    if (!sidebar.classList.contains('open')) {
        sidebar.classList.add('open');
        overlay.classList.add('visible');
    }
}

// Checkout Logic

