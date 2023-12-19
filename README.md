**Nếu thấy project này có ích có thể cho mình 1 sao** ⭐
## Hướng dẫn cài đặt:
Mở ứng dụng XAMPP, tạo cơ sở dữ liệu có tên là pdf_convertion và import dữ liệu từ file sql vào
![image](https://github.com/taiphanvan2k3/PdfConvertion/assets/108993284/8f6922e7-a9f1-4a1e-aed8-41ebd3775678)
![image](https://github.com/taiphanvan2k3/PdfConvertion/assets/108993284/3b4a2918-56fa-471f-ae35-db8f587a44cd)
![image](https://github.com/taiphanvan2k3/PdfConvertion/assets/108993284/4b4ad4d5-d5ab-4eaa-8561-32e7083a4fa8)

**Chọn import ở cuối trang**
![image](https://github.com/taiphanvan2k3/PdfConvertion/assets/108993284/f903430a-2b3a-4dea-9990-0dc29c185d81)
## 1. Giao diện của ứng dụng sau khi chạy project
Sử dụng tài khoản có sẵn trong cơ sở dữ liệu hoặc sử dụng chức năng đăng kí để tạo tài khoản mới. Nếu dùng tài khoản có sẵn thì vào database pdf_convertion, tại bảng users, lựa chọn bất kì tài khoản nào có sẵn để dùng:
![image](https://github.com/taiphanvan2k3/PdfConvertion/assets/108993284/59c0e86a-f048-410b-92aa-3b604945ff8d)
## 2.	Chức năng đăng nhập:
Nhấn vào “Login” trên màn hình, một login modal sẽ mở ra để cho phép đăng nhập và thực hiện nhấn vào Login để đăng nhập. Nếu đăng nhập bị sai thì modal này vẫn sẽ hiển thị lại cho người dùng nhập lại
![image](https://github.com/taiphanvan2k3/PdfConvertion/assets/108993284/fef36ef3-afff-4383-b21c-7dc040921c13)
## 3.	Chức năng đăng kí:
Nhập đầy đủ thông tin và nhấn Ok để tiến hành đăng kí tài khoản
![image](https://github.com/taiphanvan2k3/PdfConvertion/assets/108993284/7610a2af-db5d-44da-8e11-715e67afdb45)
## 4.	Chức năng convert file và xem lịch sử:
Giao diện sau khi đăng nhập hoặc đăng kí thành công:
![image](https://github.com/taiphanvan2k3/PdfConvertion/assets/108993284/84348c75-78e1-4669-97c2-645cac6de775)
**Choose PDF file**: cho phép chọn 1 file pdf để thực hiện convert qua file docx
**View list converted**: xem lại lịch sử đã convert, tại đây người dùng sẽ thấy được tên file upload, tên file sau convert (có thể bấm vào để tải), và ngày thực hiện
Khi **chưa thực hiện** lần convert nào thì chưa lưu lại lịch sử:
![image](https://github.com/taiphanvan2k3/PdfConvertion/assets/108993284/6e73ffbd-5cd5-4666-9b8d-536b945a56d8)

Để thực hiện việc convert thì quay về trang chủ thông qua **“Back to home”**. Chọn vào nút **“Choose PDF file”**, 1 hộp thoại sẽ hiển thị ra để cho phép chọn file để convert. Quá trình sẽ diễn ra trong 1 khoảng thời gian tuỳ vào số lượng trang của file (và vì sử dụng phiên bản free của thư viện nên đôi lúc một số file có số trang quá lớn, khoảng trên 500 trang thì sẽ gặp chút vấn đề lúc gộp các file docx lại thành 1 file docx lớn). Sau khi quá trình diễn ra hoàn thành, trang web sẽ tự động hiển thị hộp thoại để người dùng chọn vị trí lưu về máy. 
Và cũng có thể vào trang lịch sử để xem kết quả, có thể tải kết quả conver các lần trước đó về lại máy bằng cách nhấn vào đường link tại cột **File converted**
![image](https://github.com/taiphanvan2k3/PdfConvertion/assets/108993284/aab45fa1-c17e-4be2-985e-bc1e0a7e156d)

## Lưu ý:
Trên thanh menu, chọn **Project -> Clean**, chọn project **PDFToWord** và chọn Clean để build lại project, đảm bảo hoạt động đúng
![image](https://github.com/taiphanvan2k3/PdfConvertion/assets/108993284/fce14900-bcf0-4f79-b6fd-28c78475e1fe)
