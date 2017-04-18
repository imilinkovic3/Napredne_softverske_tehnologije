function selectRole() {
    var selectBox = document.getElementById("selectRole");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;

    if (selectedValue != 'Role') {
        $.ajax({
            url: "/nst/role/change/",
            type: 'POST',
            data: selectedValue,
            contentType: 'application/json',
            success: function (data) {
                $('#defaultLayoutBody').html(data);
            },
            error: function (data, status, er) {
                console.log(data + " " + status + " " + er);
            }
        });
    }
}