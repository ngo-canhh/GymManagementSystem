document.addEventListener('DOMContentLoaded', function () {
    const yearSelect = document.getElementById('year-select');
    const monthSelect = document.getElementById('month-select');
    const calculateSalaryBtn = document.getElementById('calculate-salary-btn');
    const salaryTable = document.getElementById('salary-table');

    function populateYearOptions() {
        const currentYear = new Date().getFullYear();
        yearSelect.innerHTML = ''; 

        const currentYearOption = document.createElement('option');
        currentYearOption.value = currentYear;
        currentYearOption.text = currentYear;
        yearSelect.appendChild(currentYearOption);

        const previousYearOption = document.createElement('option');
        previousYearOption.value = currentYear - 1;
        previousYearOption.text = currentYear - 1;
        yearSelect.appendChild(previousYearOption);
    }

    populateYearOptions();

    calculateSalaryBtn.addEventListener('click', function () {
        const selectedYear = yearSelect.value;
        const selectedMonth = monthSelect.value;

        calculateSalaries(selectedMonth, selectedYear)
    });

    async function calculateSalaries(month, year) {
        try {
            const response = await fetch(`/api/salaries/calculate?month=${month}&year=${year}`, { method: 'POST' });

            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            const salaries = await response.json();

            if (salaries && salaries.length > 0) {
                salaryTable.classList.remove('d-none');
                displaySalaryTable(salaries);

            } else {
                alert("Không có dữ liệu lương nào được tính toán");
                salaryTable.innerHTML = ''; 
                salaryTable.classList.add('d-none'); 
            }

        } catch (error) {
            console.error('Lỗi khi gọi API tính lương:', error);
            alert("Có lỗi xảy ra khi tính lương");
             salaryTable.classList.add('d-none');
        }
    }

    function displaySalaryTable(salaries) {
        let tableHTML = `
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Nhân viên</th>
                        <th>Lương</th>
                        
                    </tr>
                </thead>
                <tbody>
          `;
        salaries.forEach(salary => {
            tableHTML += `
                <tr>
                    <td>${salary.employeeId}</td>
                    <td>${salary.amount}</td>
                   
                </tr>
              `;
        });
        tableHTML += `
            </tbody>
            </table>
            `;
        salaryTable.innerHTML = tableHTML;
    }
});