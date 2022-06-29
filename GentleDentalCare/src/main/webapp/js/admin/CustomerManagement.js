var app = angular.module("myapp", []);

function ManageUserAPI(urlServlet, APIJson) {
    app.controller("viewCril", function (Excel, $timeout, $scope) {
        $scope.test = {};
        $scope.index = -1;
        
        // disable
        $scope.disable = function (id, action) {
            Swal.fire({
                title: 'Are you sure?',
                text: "Do you want to disable this account?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, disable it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    $.ajax({
                        url: urlServlet,
                        type: "get",
                        data: {
                            UserID: id,
                            Action: action
                        },
                        success: function () {
                            location.reload();
                        }
                    });
                }
            });
        };

        $scope.listCustomer = APIJson;

        //Xuất file Excel
        $scope.exportToExcel = function (tableId) {
            var exportHref = Excel.tableToExcel(tableId, "WireWorkbenchDataExport");
            $timeout(function () {
                location.href = exportHref;
            }, 100);
        };
    });
}

app.factory("Excel", function ($window) {
    var uri = "data:application/vnd.ms-excel;base64,",
            template =
            '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>',
            base64 = function (s) {
                return $window.btoa(unescape(encodeURIComponent(s)));
            },
            format = function (s, c) {
                return s.replace(/{(\w+)}/g, function (m, p) {
                    return c[p];
                });
            };
    return {
        tableToExcel: function (tableId, worksheetName) {
            var table = $(tableId),
                    ctx = {worksheet: worksheetName, table: table.html()},
                    href = uri + base64(format(template, ctx));
            return href;
        }
    };
});