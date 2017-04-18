function addDocumentDescriptor() {
    var table = document.getElementById("descriptorsTable");
    var table_len = (table.rows.length);

    var row = table.insertRow(table_len).outerHTML =
        "<tr id='row" + table_len + "'>" +
            "<input name='documentDescriptors[" + table_len + "].id' value='0' type='hidden'>" +
            "<td id='descriptorName" + table_len + "'>" +
                "<input name='documentDescriptors[" + table_len + "].name' placeholder='Descriptor name'" +
                " class='form-control' id='descriptorNameInput" + table_len + "'/>" +
            "</td>" +
            "<td id='descriptorType" + table_len + "'>" +
                "<select name='documentDescriptors[" + table_len + "].descriptorType' class='form-control' id='types'>" +
                    "<option value='-'>--Please select--</option>" +
                    "<option value='Number'>Number</option>" +
                    "<option value='Decimal'>Decimal</option>" +
                    "<option value='Text'>Text</option>" +
                    "<option value='Date'>Date</option>" +
                    "<option value='Logical'>Logical</option>" +
                "</select>" +
            "</td>" +
            "<td id='descriptorRequired" + table_len + "' align='center'>" +
                "<input id='documentDescriptors[" + table_len + "].required" + table_len + "' name='documentDescriptors[" + table_len + "].required' type='checkbox' value='true'>" +
            "</td>" +
            "<td>" +
                "<input type='button' class='btn btn-danger' onclick=removeDescriptor('row" + table_len + "') value='Remove descriptor'/>" +
            "</td>" +
        "</tr>";
}

function removeDescriptor(rowId) {
    var row = document.getElementById(rowId);
    var table = row.parentNode;
    while (table && table.tagName != 'TABLE')
        table = table.parentNode;
    if (!table)
        return;
    table.deleteRow(row.rowIndex);
}