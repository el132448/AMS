document.addEventListener('DOMContentLoaded', function() {
    loadEmployees(1);

    // Attach event listeners to filter input fields
    document.querySelectorAll('#filterForm input').forEach(input => {
        input.addEventListener('change', function() {
            loadEmployees(1); // Reload with updated filter criteria
        });
    });
});

function stopPropagation(event) {
    event.stopPropagation(); // Prevents the click event from bubbling up to the parent <tr>
}

function validateCheckboxes() {
    const checkboxes = document.querySelectorAll('input[name="deleteEmployeeIds"]');
    const isChecked = Array.from(checkboxes).some(checkbox => checkbox.checked);
    if (!isChecked) {
        alert('少なくとも1つのチェックボックスを選択してください。');
        return false; // Prevent form submission
    }
    return true; // Allow form submission
}

function loadEmployees(page) {
    const formData = new URLSearchParams(new FormData(document.querySelector('#filterForm')));
    formData.append('page', page);

    fetch(`/employee/api/employees?${formData.toString()}`, {
        method: 'GET'
    })
        .then(response => response.json())
        .then(data => {
            // Populate the employee table
            const employeeTableBody = document.querySelector('#employeeTableBody');
            employeeTableBody.innerHTML = '';
            data.content.forEach(employee => {
                const row = document.createElement('tr');
                row.className = 'clickable-row';
                row.onclick = () => window.location.href = `/employee/${employee.employeeId}`;

                row.innerHTML = `
                <td class="checkbox-cell" onclick="stopPropagation(event)">
                    <input type="checkbox" value="${employee.employeeId}" name="deleteEmployeeIds">
                </td>
                <td>${employee.employeeId}</td>
                <td>${employee.employeeName}</td>
                <td>${employee.employeeGender}</td>
                <td>${employee.employeeBirthDate}</td>
                <td>${employee.employeeAge}</td>
                <td>${employee.employeeJoiningDate}</td>
                <td>${employee.employeeDepartment}</td>
                <td>${employee.employeeEmail}</td>
            `;
                employeeTableBody.appendChild(row);
            });

            // Update pagination controls
            const paginationControls = document.querySelector('#paginationControls .pagination');
            paginationControls.innerHTML = '';

            if (data.totalPages > 1) {
                // Previous button
                if (data.number > 0) {
                    paginationControls.innerHTML += `<li><button onclick="loadEmployees(${data.number})">前へ</button></li>`;
                } else {
                    paginationControls.innerHTML += `<li><button disabled>前へ</button></li>`;
                }

                // Page number buttons
                for (let i = 1; i <= data.totalPages; i++) {
                    if (i === data.number + 1) {
                        paginationControls.innerHTML += `<li><button disabled>${i}</button></li>`;
                    } else {
                        paginationControls.innerHTML += `<li><button onclick="loadEmployees(${i})">${i}</button></li>`;
                    }
                }

                // Next button
                if (data.number < data.totalPages - 1) {
                    paginationControls.innerHTML += `<li><button onclick="loadEmployees(${data.number + 2})">次へ</button></li>`;
                } else {
                    paginationControls.innerHTML += `<li><button disabled>次へ</button></li>`;
                }
            }
        })
        .catch(() => {
            alert('データの取得に失敗しました。');
        });
}
