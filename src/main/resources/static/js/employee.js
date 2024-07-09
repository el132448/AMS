$(document).ready(function() {
    loadEmployees(1);
});

function stopPropagation(event) {
    event.stopPropagation(); // Prevents the click event from bubbling up to the parent <tr>
}

function validateCheckboxes() {
    var checkboxes = document.querySelectorAll('input[name="deleteEmployeeIds"]');
    var isChecked = Array.from(checkboxes).some(checkbox => checkbox.checked);
    if (!isChecked) {
        alert('少なくとも1つのチェックボックスを選択してください。');
        return false; // Prevent form submission
    }
    return true; // Allow form submission
}

function loadEmployees(page) {
    var formData = $('#filterForm').serialize() + '&page=' + page;
    $.ajax({
        url: '/employee/api/employees',
        type: 'GET',
        data: formData,
        success: function(data) {
            // Populate the employee table
            var employeeTableBody = $('#employeeTableBody');
            employeeTableBody.empty();
            $.each(data.content, function(index, employee) {
                var row = '<tr class="clickable-row" onclick="window.location.href=\'/employee/' + employee.employeeId + '\'">' +
                    '<td class="checkbox-cell" onclick="stopPropagation(event)">' +
                    '<input type="checkbox" value="' + employee.employeeId + '" name="deleteEmployeeIds">' +
                    '</td>' +
                    '<td>' + employee.employeeId + '</td>' +
                    '<td>' + employee.employeeName + '</td>' +
                    '<td>' + employee.employeeGender + '</td>' +
                    '<td>' + employee.employeeBirthDate + '</td>' +
                    '<td>' + employee.employeeAge + '</td>' +
                    '<td>' + employee.employeeJoiningDate + '</td>' +
                    '<td>' + employee.employeeDepartment + '</td>' +
                    '<td>' + employee.employeeEmail + '</td>' +
                    '</tr>';
                employeeTableBody.append(row);
            });

            // Update pagination controls
            var paginationControls = $('#paginationControls .pagination');
            paginationControls.empty();

            if (data.totalPages > 1) {
                // 前へ
                if (data.number > 0) {
                    paginationControls.append('<li><button onclick="loadEmployees(' + (data.number) + ')">前へ</button></li>');
                }else{
                    paginationControls.append('<li><button disabled>前へ</button></li>');
                }

                // page number
                for (var i = 1; i <= data.totalPages; i++) {
                    if (i === data.number + 1) {
                        paginationControls.append('<li><button disabled>' + i + '</button></li>');
                    } else {
                        paginationControls.append('<li><button onclick="loadEmployees(' + i + ')">' + i + '</button></li>');
                    }
                }

                // 次へ
                if (data.number < data.totalPages - 1) {
                    paginationControls.append('<li><button onclick="loadEmployees(' + (data.number + 2) + ')">次へ</button></li>');
                }else{
                    paginationControls.append('<li><button disabled>次へ</button></li>');
                }
            }
        },
        error: function() {
            alert('データの取得に失敗しました。');
        }
    });
}
