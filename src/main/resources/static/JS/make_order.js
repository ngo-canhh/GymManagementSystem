document.addEventListener('DOMContentLoaded', function () {
    const user_id = document.getElementById('customer-id');
    const item_tablebody = document.querySelector('#added-item tbody');

    const totalSpan = document.getElementById('total');

    
    function updateTotal() {
        let total = 0;
        // Lấy tất cả các hàng trong bảng
        const rows = item_tablebody.querySelectorAll("tr");
        rows.forEach(row => {
            const totalCell = row.querySelector("td:nth-child(5)"); // Cột tổng tiền
            total += parseFloat(totalCell.textContent) || 0;
        });
        // Cập nhật giá trị tổng
        totalSpan.textContent = `${total.toFixed(2)} VND`;
    }

    user_id.addEventListener('input', () => {
        const id = user_id.value.trim();
        console.log(id);
        if (!id) {
            console.log('No ID entered');
            return;
        }

        fetch(`/api/user_info?id=${id}`)
            .then(response => {
                if (!response.ok) {
                    return response.text().then(err => {
                        throw new Error(err);
                    });
                }
                return response.json();
            })
            .then(data => {
                console.log(data);
                const user_name = document.getElementById('customer-name');
                const user_salary = document.getElementById('customer-salary');
                const user_address = document.getElementById('customer-address');
                user_name.value = data.full_name; // Đảm bảo key "name" tồn tại trong JSON
                user_salary.value = data.salary;
                user_address.value = data.address;
            })
            .catch(error => {
                console.error('Error:', error.message);
                alert('Customer not found or an error occurred');
            });
    });

    const item_code = document.getElementById('item-code');
    item_code.addEventListener('input', () => {
        const code = item_code.value.trim();
        console.log(code);
        if (!code) {
            console.log('No code entered');
            return;
        }

        fetch(`/api/item_info?id=${code}`)
            .then(response => {
                if (!response.ok) {
                    return response.text().then(err => {
                        throw new Error(err);
                    });
                }
                return response.json();
            })
            .then(data => {
                console.log(data);
                const item_name = document.getElementById('item-name');
                const item_price = document.getElementById('price');
                item_name.value = data.name; // Đảm bảo key "name" tồn tại trong JSON
                item_price.value = data.sale_price;
            })
            .catch(error => {
                console.error('Error:', error.message);
                alert('Item not found or an error occurred');
            });
    }); 


    const add_button = document.getElementById('add-item');
    
    add_button.addEventListener('click', () => {
        const item_code = document.getElementById('item-code').value.trim();
        const item_name = document.getElementById('item-name').value.trim();
        const item_price = document.getElementById('price').value.trim();
        const quantity = document.getElementById('quantity').value.trim();
        const total = item_price * quantity;
        
        const new_row = document.createElement('tr');
        new_row.innerHTML = `
            <td>${item_code}</td>
            <td>${item_name}</td>
            <td>${item_price}</td>
            <td>${quantity}</td>
            <td>${total}</td>
            <td><button class="btn-delete">Xóa</button></td>
        `;

        const delete_button = new_row.querySelector('.btn-delete');
        delete_button.addEventListener('click', () => {
            new_row.remove();
            updateTotal();
        });
        item_tablebody.appendChild(new_row);

        updateTotal();
        document.getElementById('item-code').value = '';
        document.getElementById('item-name').value = '';
        document.getElementById('price').value = '';
        document.getElementById('quantity').value = '';

    }); 





    //Gói thông tin để thanh toán


    const submit = document.getElementById('btn-purchase');
    submit.addEventListener('click', function(event){
        event.preventDefault();
        const customer_form = {
            id : parseInt(document.getElementById('customer-id').value),
            name : document.getElementById('customer-name').value,
            salary : parseFloat(document.getElementById('customer-salary').value),
            address : document.getElementById('customer-address').value
        };  
        
        const items = [];
        const rows = item_tablebody.querySelectorAll('tr');
        rows.forEach(row => {
            const cells = row.querySelectorAll('td');
            const item = {
                code: parseInt(cells[0].textContent),
                name: cells[1].textContent,
                price: parseFloat(cells[2].textContent),
                quantity: parseInt(cells[3].textContent),
                total: parseFloat(cells[4].textContent)
            };
            items.push(item);
        });
        
        const idStaff = parseInt(document.getElementById('staff-id').value);

        const staff = {
            id: idStaff
        };
        
        const order = {
            customer: customer_form,
            items: items,
            total: parseFloat(totalSpan.textContent),
            staff: staff
        };

        console.log("send order", order);

        fetch('/api/make_new_order',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(order),
        })
        .then(response => {
            console.log("response status", response.status);
            if (!response.ok) {
                return response.text().then(err => {
                    throw new Error(err);
                });
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            alert('Order created successfully');
            window.location.href = '/';
        })
        
    });
   

    const submit_by_card = document.getElementById('btn-purchase-card');
    submit_by_card.addEventListener('click', function(event){
        const customer_form = {
            id : parseInt(document.getElementById('customer-id').value),
            name : document.getElementById('customer-name').value,
            salary : parseFloat(document.getElementById('customer-salary').value),
            address : document.getElementById('customer-address').value
        };  
        
        const items = [];
        const rows = item_tablebody.querySelectorAll('tr');
        rows.forEach(row => {
            const cells = row.querySelectorAll('td');
            const item = {
                code: parseInt(cells[0].textContent),
                name: cells[1].textContent,
                price: parseFloat(cells[2].textContent),
                quantity: parseInt(cells[3].textContent),
                total: parseFloat(cells[4].textContent)
            };
            items.push(item);
        });
        
        const idStaff = parseInt(document.getElementById('staff-id').value);

        const staff = {
            id: idStaff
        };
        
        const order = {
            customer: customer_form,
            items: items,
            total: parseFloat(totalSpan.textContent),
            staff: staff
        };

        console.log("send order", order);

        fetch('/api/make_new_order_card',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(order),
        })
        .then(response => {
            console.log("response status", response.status);
            if (!response.ok) {
                return response.text().then(err => {
                    throw new Error(err);
                });
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            if (data.redirectUrl) {
                window.location.href = data.redirectUrl;
            }
        })
    });

});
