USE master
GO

IF EXISTS (SELECT * FROM sys.databases WHERE name='Project_S2')
DROP DATABASE Project_S2
GO

CREATE DATABASE Project_S2
GO

USE Project_S2
GO

CREATE TABLE [GiaoVu]
(
[TenTaiKhoan] VARCHAR(50),
CONSTRAINT PK_TenTaiKhoan PRIMARY KEY(TenTaiKhoan),
[MatKhau] VARCHAR(50) NOT NULL,
[HoTen] NVARCHAR(200) NOT NULL,
[Email] VARCHAR(100) NOT NULL,
[NgaySinh] DATE,
[DienThoai] VARCHAR(20),
[DiaChi] TEXT,
[IsDeleted] BIT DEFAULT 0
)

CREATE TABLE [NganHangCauHoi]
(
[MaCauHoi] INT IDENTITY,
CONSTRAINT PK_MaCauhoiToan PRIMARY KEY(MaCauHoi),
[MonThi] NVARCHAR(50) NOT NULL,
[NoiDung] NVARCHAR(MAX) NOT NULL,
[TraLoi1] NVARCHAR(MAX) NOT NULL,
[TraLoi2] NVARCHAR(MAX) NOT NULL,
[TraLoi3] NVARCHAR(MAX) NOT NULL,
[TraLoi4] NVARCHAR(MAX) NOT NULL,
[DapAn] INT NOT NULL,
[IsDeleted] BIT DEFAULT 0
)

/*
CREATE TABLE [NganHangCauHoiToan]
(
[MaCauHoi] INT IDENTITY,
CONSTRAINT PK_MaCauhoiToan PRIMARY KEY(MaCauHoi),
[NoiDung] NVARCHAR(MAX) NOT NULL,
[TraLoi1] NVARCHAR(MAX) NOT NULL,
[TraLoi2] NVARCHAR(MAX) NOT NULL,
[TraLoi3] NVARCHAR(MAX) NOT NULL,
[TraLoi4] NVARCHAR(MAX) NOT NULL,
[DapAn] INT NOT NULL,
[IsDeleted] BIT DEFAULT 0
)

CREATE TABLE [NganHangCauHoiSu]
(
[MaCauHoi] INT IDENTITY,
CONSTRAINT PK_MaCauhoiSu PRIMARY KEY(MaCauHoi),
[NoiDung] NVARCHAR(MAX) NOT NULL,
[TraLoi1] NVARCHAR(MAX) NOT NULL,
[TraLoi2] NVARCHAR(MAX) NOT NULL,
[TraLoi3] NVARCHAR(MAX) NOT NULL,
[TraLoi4] NVARCHAR(MAX) NOT NULL,
[DapAn] INT NOT NULL,
[IsDeleted] BIT DEFAULT 0
)

CREATE TABLE [NganHangCauHoiVan]
(
[MaCauHoi] INT IDENTITY,
CONSTRAINT PK_MaCauhoiVan PRIMARY KEY(MaCauHoi),
[NoiDung] NVARCHAR(MAX) NOT NULL,
[TraLoi1] NVARCHAR(MAX) NOT NULL,
[TraLoi2] NVARCHAR(MAX) NOT NULL,
[TraLoi3] NVARCHAR(MAX) NOT NULL,
[TraLoi4] NVARCHAR(MAX) NOT NULL,
[DapAn] INT NOT NULL,
[IsDeleted] BIT DEFAULT 0
)
*/

CREATE TABLE [DeToan]
(
[MaDeToan] INT IDENTITY(1000,1),
CONSTRAINT PK_MaDeToan PRIMARY KEY(MaDeToan),
[MaCauHoiToan1] INT,
CONSTRAINT FK_NganHangCauHoi_DeToan1 FOREIGN KEY(MaCauHoiToan1) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiToan2] INT,
CONSTRAINT FK_NganHangCauHoi_DeToan2 FOREIGN KEY(MaCauHoiToan2) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiToan3] INT,
CONSTRAINT FK_NganHangCauHoi_DeToan3 FOREIGN KEY(MaCauHoiToan3) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiToan4] INT,
CONSTRAINT FK_NganHangCauHoi_DeToan4 FOREIGN KEY(MaCauHoiToan4) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiToan5] INT,
CONSTRAINT FK_NganHangCauHoi_DeToan5 FOREIGN KEY(MaCauHoiToan5) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiToan6] INT,
CONSTRAINT FK_NganHangCauHoi_DeToan6 FOREIGN KEY(MaCauHoiToan6) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiToan7] INT,
CONSTRAINT FK_NganHangCauHoi_DeToan7 FOREIGN KEY(MaCauHoiToan7) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiToan8] INT,
CONSTRAINT FK_NganHangCauHoi_DeToan8 FOREIGN KEY(MaCauHoiToan8) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiToan9] INT,
CONSTRAINT FK_NganHangCauHoi_DeToan9 FOREIGN KEY(MaCauHoiToan9) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiToan10] INT,
CONSTRAINT FK_NganHangCauHoi_DeToan10 FOREIGN KEY(MaCauHoiToan10) REFERENCES [NganHangCauHoi](MaCauHoi),
)

CREATE TABLE [DeSu]
(
[MaDeSu] INT IDENTITY(2000,1),
CONSTRAINT PK_MaDeSu PRIMARY KEY(MaDeSu),
[MaCauHoiSu1] INT,
CONSTRAINT FK_NganHangCauHoi_DeSu1 FOREIGN KEY(MaCauHoiSu1) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiSu2] INT,
CONSTRAINT FK_NganHangCauHoi_DeSu2 FOREIGN KEY(MaCauHoiSu2) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiSu3] INT,
CONSTRAINT FK_NganHangCauHoi_DeSu3 FOREIGN KEY(MaCauHoiSu3) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiSu4] INT,
CONSTRAINT FK_NganHangCauHoi_DeSu4 FOREIGN KEY(MaCauHoiSu4) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiSu5] INT,
CONSTRAINT FK_NganHangCauHoi_DeSu5 FOREIGN KEY(MaCauHoiSu5) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiSu6] INT,
CONSTRAINT FK_NganHangCauHoi_DeSu6 FOREIGN KEY(MaCauHoiSu6) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiSu7] INT,
CONSTRAINT FK_NganHangCauHoi_DeSu7 FOREIGN KEY(MaCauHoiSu7) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiSu8] INT,
CONSTRAINT FK_NganHangCauHoi_DeSu8 FOREIGN KEY(MaCauHoiSu8) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiSu9] INT,
CONSTRAINT FK_NganHangCauHoi_DeSu9 FOREIGN KEY(MaCauHoiSu9) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiSu10] INT,
CONSTRAINT FK_NganHangCauHoi_DeSu10 FOREIGN KEY(MaCauHoiSu10) REFERENCES [NganHangCauHoi](MaCauHoi),
[IsDeleted] BIT DEFAULT 0
)

CREATE TABLE [DeVan]
(
[MaDeVan] INT IDENTITY(3000,1),
CONSTRAINT PK_MaDeVan PRIMARY KEY(MaDeVan),
[MaCauHoiVan1] INT,
CONSTRAINT FK_NganHangCauHoi_DeVan1 FOREIGN KEY(MaCauHoiVan1) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiVan2] INT,
CONSTRAINT FK_NganHangCauHoi_DeVan2 FOREIGN KEY(MaCauHoiVan2) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiVan3] INT,
CONSTRAINT FK_NganHangCauHoi_DeVan3 FOREIGN KEY(MaCauHoiVan3) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiVan4] INT,
CONSTRAINT FK_NganHangCauHoi_DeVan4 FOREIGN KEY(MaCauHoiVan4) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiVan5] INT,
CONSTRAINT FK_NganHangCauHoi_DeVan5 FOREIGN KEY(MaCauHoiVan5) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiVan6] INT,
CONSTRAINT FK_NganHangCauHoi_DeVan6 FOREIGN KEY(MaCauHoiVan6) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiVan7] INT,
CONSTRAINT FK_NganHangCauHoi_DeVan7 FOREIGN KEY(MaCauHoiVan7) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiVan8] INT,
CONSTRAINT FK_NganHangCauHoi_DeVan8 FOREIGN KEY(MaCauHoiVan8) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiVan9] INT,
CONSTRAINT FK_NganHangCauHoi_DeVan9 FOREIGN KEY(MaCauHoiVan9) REFERENCES [NganHangCauHoi](MaCauHoi),
[MaCauHoiVan10] INT,
CONSTRAINT FK_NganHangCauHoi_DeVan10 FOREIGN KEY(MaCauHoiVan10) REFERENCES [NganHangCauHoi](MaCauHoi),
[IsDeleted] BIT DEFAULT 0
)

CREATE TABLE [QuanLyDeThi]
(
[MaDe] VARCHAR(20),
CONSTRAINT PK_MaDe PRIMARY KEY(MaDe),
[TenTaiKhoan] VARCHAR(50),
CONSTRAINT FK_GiaoVu_QuanLyDeThi FOREIGN KEY(TenTaiKhoan) REFERENCES [GiaoVu](TenTaiKhoan),
[NgayTaoDe] DATE,
[MaDeToan] INT,
CONSTRAINT FK_DeToan_QuanLyDeThi FOREIGN KEY(MaDeToan) REFERENCES [DeToan](MaDeToan),
[MaDeSu] INT,
CONSTRAINT FK_DeSu_QuanLyDeThi FOREIGN KEY(MaDeSu) REFERENCES [DeSu](MaDeSu),
[MaDeVan] INT,
CONSTRAINT FK_DeVan_QuanLyDeThi FOREIGN KEY(MaDeVan) REFERENCES [DeVan](MaDeVan),
[IsDeleted] BIT DEFAULT 0
)

CREATE TABLE [ThiSinh]
(
[MaThiSinh] VARCHAR(20),
CONSTRAINT PK_MaThiSinh PRIMARY KEY(MaThiSinh),
[HoTen] TEXT NOT NULL,
[NgaySinh] DATE NOT NULL,
[MaDe] VARCHAR(20),
CONSTRAINT FK_QuanLyDeThi_ThiSinh FOREIGN KEY(MaDe) REFERENCES [QuanLyDeThi](MaDe),
[IsDeleted] BIT DEFAULT 0
)

CREATE TABLE [KetQua]
(
[MaKetQua] INT IDENTITY,
CONSTRAINT PK_KetQua PRIMARY KEY(MaKetQua),
[MaDe] VARCHAR(20),
CONSTRAINT FK_QuanLyDeThi_KetQua FOREIGN KEY(MaDe) REFERENCES [QuanLyDeThi](MaDe),
[MaThiSinh] VARCHAR(20),
CONSTRAINT FK_ThiSinh_KetQua FOREIGN KEY(MaThiSinh) REFERENCES [ThiSinh](MaThiSinh),
[NgayThi] DATE,
[DiemToan] INT NOT NULL,
[DiemSu] INT NOT NULL,
[DiemVan] INT NOT NULL,
[IsDeleted] BIT DEFAULT 0
)

--INSERT DATA
INSERT INTO [GiaoVu](TenTaiKhoan,MatKhau,HoTen,Email,NgaySinh,DienThoai,DiaChi) VALUES
('admin','21232f297a57a5a743894a0e4a801fc3','Admin NOC','admin.noc@vietnamairlines.com','2015-12-12','0918442968','Ha Noi, Viet Nam')

INSERT INTO [NganHangCauHoi](MonThi,NoiDung,TraLoi1,TraLoi2,TraLoi3,TraLoi4,DapAn) VALUES
(N'History',N'Mục đích khai thác thuộc địa lần thứ hai của Pháp ở Việt Nam là gì?',N'Bù vào những thiệt hại trong cuộc khai thác lần thứ nhất.',N'Để bù đắp những thiệt hại do Chiến tranh thế giới lần thứ nhất gây ra.',N'Để thúc đẩy sự phát triển kinh tế – xã hội ở Việt Nam.',N'Để tăng cường sức mạnh về kinh tế của Pháp đối với các nước tư bản chủ nghĩa.',2),
(N'History',N'Trong cuộc khai thác thuộc địa lần thứ hai, thực dân Pháp đầu tư vốn nhiều nhất vào các ngành nào?',N'Công nghiệp chế biến.',N'Nông nghiệp và khai thác mỏ.',N'Nông nghiệp và thương nghiệp.',N'Giao thông vận tải.',2),
(N'History',N'Vì sao tư bản Pháp chú trọng đến việc khai thác mỏ than ở Việt Nam?',N'ở Việt Nam có trữ lượng than lớn.',N'Than là nguyên liệu chủ yếu phục vụ cho công nghiệp chính quốc.',N'Để phục vụ cho nhu cầu công nghiệp chính quốc',N'Tất cả cùng đúng.',4),
(N'History',N'Vì sao trong quá trình khai thác thuộc địa lần thứ hai, tư bản Pháp hạn chế phát triển công nghiệp nặng ở Việt Nam?',N'Cột chặt nền kinh tế Việt Nam lệ thuộc vào kinh tế Pháp.',N'Biến Việt Nam thành thị trường tiêu thụ hàng hóa do nền công nghiệp Pháp sản xuất',N'Biến Việt Nam thành căn cứ quân sự và chính trị của Pháp',N'Câu A và B đều đúng',4),
(N'History',N'Các giai cấp nào mới ra đời do hậu quả của cuộc khai thác thuộc địa lần thứ hai của Pháp ở Việt Nam?',N'Công nhân, nông dân, tư sản dân tộc',N'Công nhân, tiểu tư sản, tư sản dân tộc',N'Công nhân, tư sản dân tộc, địa chủ phong kiến',N'Công nhân, nông dân, tư sản dân tộc, tiểu tư sản, địa chủ phong kiến',2),
(N'History',N'Sau chiến tranh thế giới lần thứ nhất, ở Việt Nam ngoài thực dân Pháp, còn có giai cấp nào trở thành đối tượng của cách mạng Việt Nam?',N'Giai cấp nông dân',N'Giai cấp công nhân',N'Giai cấp đại địa chủ phong kiến',N'Giai cấp tư sản, dân tộc',3),
(N'History',N'Dưới ách thống trị của thực dân Pháp, thái độ chính trị của giai cấp tư sản dân tộc Việt Nam như thế nào?',N'Có thái độ kiên định với Pháp',N'Có thái độ không kiên định, dễ thoải hiệp, cải lương khi đế quốc mạnh',N'Có tinh thần đấu tranh cách mạng triệt để trong sự nghiệp giải phóng dân tộc.',N'Tất cả các câu trên đều đúng.',2),
(N'History',N'Những tổ chức chính trị như: Việt Nam Nghĩa đoàn, Hội Phục Việt, Hội Hưng Nam, Đảng Thanh Niên là tiền thân của tổ chức nào?',N'Hội Việt Nam cách mạng thanh niên.',N'Việt Nam quốc dân đảng.',N'Tân Việt cách mạng đảng',N'Đông Dương Cộng sản đảng',3),
(N'History',N'Trần Dân Tiên viết: "việc đó tuy nhỏ nhưng nó báo hiệu bắt đầu thời đại đấu tranh dân tộc như chim én nhỏ báo hiệu mùa xuân". Sự kiện nào sau đây phản ánh điều đó?',N'Cuộc bãi công của công nhân Ba Son.',N'Cuộc đấu tranh đòi nhà cầm quyền Pháp thả Phan Bội Châu (1925).',N'Phong trào để tang Phan Châu Trinh (1926)',N'Tiếng bom Phạm Hồng Thái tại Sa Diện- Quảng Châu (6/1924)',4),
(N'History',N'Sự kiện nào đánh dấu Nguyễn ái Quốc bước đầu tìm thấy con đường cứu nước đúng đắn',N'Nguyễn ái Quốc đưa yêu sách đến Hội nghị vecxai (18/6/1919)',N'Nguyễn ái Quốc tham gia sáng lập Đảng Cộng sản Pháp (12/1920)',N'Nguyễn ái Quốc đọc sơ thảo luận cương của Lênin về vấn đề dân tộc và thuộc địa (7/1920)',N'Nguyễn ái Quốc thành lập tổ chức Hội Việt Nam cách mạng thanh niên(6/1925)',3),
(N'History',N'Vào thời gian nào, Nguyễn ái Quốc rời Pari đi Liên Xô, đất nước mà từ lâu Người mơ ước đặt chân tới',N'Tháng 6/1924',N'Tháng 6/1922',N'Tháng 12/1923',N'Tháng 6/1923',4),
(N'History',N'Sự kiện ngày 17/6/1924 gắn với hoạt động nào của Nguyễn ái Quốc ở Liên Xô, đó là',N'Người dự đại hội Nông dân quốc tế',N'Người dự đại hội lần thứ V của quốc tế cộng sản',N'Người dự đại hội quốc tế phụ nữ',N'Người dự đại hội quốc tế VII của quốc tế cộng sản',2),
(N'History',N'Tác dụng trong quá trình hoạt động của Nguyễn ái Quốc từ năm 1919 đến 1925 là gì?',N'Quá trình chuẩn bị về tư tưởng chính trị và tổ chức cho sự thành lập Đảng Cộng sản Việt Nam 3/2/1930',N'Quá trình truyền bá chủ nghĩa Mác – Lênin vào Việt Nam',N'Quá trình thành lập ba tổ chức Cộng sản ở Việt Nam',N'Quá trình thực hiện chủ trương “Vô sản hóa” để truyền bá chủ nghĩa Mác – Lênin vào Việt Nam',1),
(N'History',N'Hội Việt Nam cách mạng thanh niên được thành lập vào thời gian nào? ở đâu?',N'Tháng 5/1925 ở Quảng Châu (Trung Quốc).',N'Tháng 6/1925 ở Hương Cảng (Trung Quốc)',N'Tháng 7/1925 ở Quảng Châu (Trung Quốc)',N'Tháng 6/1925 ở Quảng Châu (Trung Quốc)',4),
(N'History',N'Hãy nêu rõ thành phần và địa bàn hoạt động của Tân Việt cách mạng đảng?',N'Công nhân và nông dân, hoạt động ở Trung Kì',N'Tư sản dân tộc, công nhân, hoạt động ở Bắc Kì',N'Trí thức trẻ và thanh niên tiểu tư sản, hoạt động ở Trung Kì',N'Tất cả các giai cấp và tầng lớp, hoạt động ở Nam Kì',3),
(N'History',N'Tân Việt cách mạng đảng đã phân hóa như thế nào dưới tác động của Hội Việt Nam cách mạng thanh niên?',N'Một số đảng viên tiên tiến chuyển sang Hội Việt Nam cách mạng thanh niên',N'Một số tiên tiến còn lại tích cực chuẩn bị tiến tới thành lập một chính đảng kiểu mới theo chủ nghĩa Mác – Lênin.',N'Một số gia nhập vào Việt Nam quốc dân đảng',N'Câu A và B đều đúng',4),
(N'History',N'Mục tiêu của Việt Nam quốc dân đảng là gì?',N'Đánh đuổi thực dân Pháp. xóa bỏ ngôi vua.',N'Đánh đuổi thực dân Pháp, thiết lập dân quyền',N'Đánh đuổi giặc Pháp, đánh đổ ngôi vua, thiết lập dân quyền',N'Đánh đổ ngôi vua, đánh đuổi giặc Pháp, lập nên nước Việt Nam độc lập',3),
(N'History',N'Quá trình phân hóa của Hội Việt Nam cách mạng thanh niên đã dẫn đến sự thành lập các tổ chức cộng sản nào trong năm 1929?',N'Đông Dương cộng sản đảng, An Nam cộng sản đảng',N'Đông Dương cộng sản đảng, An Nam cộng sản đảng và Đông Dương cộng sản liên đoàn',N'Đông Dương cộng sản đảng, Đông dương cộng sản liên đoàn',N'Tất cả đều sai',1),
(N'History',N'Đâu là nguyên nhân thuộc về kinh tế dẫn đến sự bùng nổ phong trào cách mạng 1930-1931',N'Thực dân Pháp áp bức bóc lột nặng nề, thêm vào đó là cuộc khủng hoảng kinh tế 1929-1933 làm cho đời sống nhân dân ta vô cùng khốn khổ',N'Thực dân Pháp thực hiện cuộc khủng bố trắng',N'Đảng ra đời đề ra khẩu hiệu "Độc lập dân tộc" và "Ruộng đất dân cày"',N'Tất cả các nguyên nhân nêu trên',1),
(N'History',N'Sự kiện nào dưới đây gắn với ngày 12 tháng 9 năm 1930',N'Bãi công của công nhân Vinh – Bến Thủy',N'Nổi dậy của nông dân Hưng Nguyên – Nam Đàn – Nghệ An',N'Nổi dậy của nông dân Thanh Chương phá đồn điền Trí Viễn',N'Bãi công của công nhân đồn điền cao su Dầu Tiếng',2),
(N'Literature',N'Dòng nào nêu đúng nhất về khái niệm thể loại truyền thuyết ?',N'Là loại truyện kể dân gian có chứa nhiều yếu tố kì lạ , hoang đường',N'Là loại truyện kể dân gian kể về các nhân vật lịch sử thời quá khứ',N'Là loại truyện kể dân gian có nhứng yếu tố hoang đường kể về các sự kiện và nhân vật lịch sử thời quá khứ',N'Là loại truyện kể dân gian kể về các sự kiện lịch sử trong thời quá khứ',3),
(N'Literature',N'Trong văn bản "Bánh chưng, bánh giày", vua Hùng lúc về già mong muốn điều gì ?',N'Muốn tìm người xứng đáng để truyền ngôi',N'Muốn nghỉ ngơi cùng con cháu',N'Muốn đi thăm thú cảnh vật của đất nước',N'Muốn đi săn bắn cùng với các quan trong triều',1),
(N'Literature',N'Trong văn bản "Bánh chưng, bánh giày", vua Hùng muốn chọn người nối ngôi như thế nào ?',N'Người nối ngôi phải là người tài giỏi',N'Người nối ngôi phải là người biết làm ruộng',N'Người nối ngôi phải là người săn bắn giỏi',N'Người nối ngôi phải là người nối được chí của nhà vua',4),
(N'Literature',N'Trong văn bản "Bánh chưng, bánh giày", Lang Liêu đã dâng lên Tiên Vương lễ vật gì ?',N'Nem công, chả phượng',N'Sơn hào, hải vị',N'Bánh chưng, bánh giày',N'Tôm đồng, cua bể',3),
(N'Literature',N'Ý nghĩa của truyện "Bánh chưng, bánh giày" là gì?',N'Ca ngợi sự thông minh, sáng suốt của Vua Hùng đời thứ sáu và phản ánh thành tựu văn minh nông nghiệp ở buổi đầu dựng nước',N'Giải thích nguồn gốc bánh chưng, bánh giày; đề cao nghề nông và thể hiện sự tôn kính trời đất, tổ tiên của nhân dân ta',N'Ca ngợi sự thông minh và khéo léo của người nông dân trong buổi đầu dựng nước và giữ nước',N'Khuyên nhủ con cháu mọi thời cần phải biết ơn, trân trọng hạt gạo vì đó là công sức lao động của người nông dân',2),
(N'Literature',N'Tác giả Tố Hữu gặp Lượm ở địa danh nào ?',N'Đồn Mang Cá',N'Hà Nội',N'Sài Gòn',N'Hàng Bè ( Huế )',4),
(N'Literature',N'Bài thơ "Mưa" của Trần Đăng Khoa được sáng tác năm nào?',N'1965',N'1966',N'1967',N'1968',3),
(N'Literature',N'"Mặt trời nhú lên dần dần, rồi lên cho kì hết. Tròn trĩnh phúc hậu như lòng đỏ một quả trứng thiên nhiên đầy đặn. Quả trứng hồng hào thăm thẳm và đường bệ đặt lên một mâm bạc đường kính mâm rộng bằng cả một cái chân trời màu ngọc trai nước biển hửng hồng. Y như một mâm lễ phẩm tiến ra từ bình minh để mong cho sự trường thọ của tất cả những người chài lưới trên muôn thuở biển Đông." Đoạn văn trên sử dụng biện pháp nghệ thuật nào là chính ?',N'Hoán dụ',N'So sánh',N'Ẩn dụ',N'Nhân hóa',2),
(N'Literature',N'Dòng nào nói đúng nhất về vai trò thành phần chính của câu ?',N'Là thành phần giúp cho câu hoàn chỉnh và diễn đạt một ý trọn vẹn',N'Là những thành phần luôn đi kèm với một số thành phần phụ',N'Là những thành phần bắt buộc phải có mặt trong câu để câu diễn đạt được một ý trọn vẹn',N'Là những thành phần bắt buộc phải có mặt để câu có cấu tạo hoàn chỉnh và diễn đạt được một ý trọn vẹn',4),
(N'Literature',N'Dòng nào nói đúng nhất về vị trí và vai trò của cây tre trong đời sống con người Việt Nam?',N'Tre là một người bạn thân thiết của nhân dân Việt Nam ta',N'Tre là một người bạn thân thiết của người nông dân,bạn thân của nhân dân Việt Nam',N'Tre gắn bó với con người trong chiến đấu bảo vệ quê hương đất nước',N'Tre gắn bó với người nông dân trong cuộc sống hàng ngày và đặc biệt là trong lao động sản xuất',2),
(N'Literature',N'Đoạn văn "Vào đâu tre cũng sống, ở đâu tre cũng xanh tốt. Dáng tre vươn mộc mạc, màu tre tươi nhũn nhặn. Rồi tre lớn lên, cứng cáp, dẻo dai, vững chắc." sử dụng biện pháp tu từ gì ?',N'Hoán dụ',N'Nhân hóa',N'Ẩn dụ',N'So sánh',2),
(N'Literature',N'Nội dung của văn bản "Lòng yêu nước" của tác giả I. Ê-ren-bua là ?',N'Những biểu hiện lòng yêu nước của nhân dân Nga-Xô viết',N'Thể hiện lòng yêu nước của nhân dân Nga-Xô viết',N'Lòng yêu nước là động lực giúp người dân đứng lên chống quân xâm lược, bảo vệ Tổ quốc.',N'Cả A, B, C',4),
(N'Literature',N'Văn bản "Lao xao" của Duy Khán có sự kết hợp của các phương thức biểu đạt nào?',N'Tự sự và nghị luận',N'Tự sự và miêu tả',N'Biểu cảm và miêu tả',N'Tự sự và thuyết minh',2),
(N'Literature',N'Trong các câu sau,câu nào không đầy đủ thành phần chính?',N'Cầu Long Biên có một tuyến đường sắt',N'Cầu Long Biên có một tuyến đường sắt chạy giữa',N'Cầu Long Biên một tuyến đường sắt chạy giữa',N'Một tuyến đường sắt chạy giữa cầu Long Biên',3),
(N'Literature',N'"Lòng thèm khát của họ sẽ ngấu nghiến đất đai, rồi để lại đằng sau những bãi hoang mạc." (Bức thư của thủ lĩnh da đỏ). Câu văn trên thể hiện điều gì?',N'Phản ánh chính sách cai trị và bóc lột thuộc địa của chủ nghĩa đế quốc',N'Phản ánh chế độ bóc lột người tàn bạo của chủ nghĩa đế quốc',N'Phản ánh hậu quả trong việc khai thác thiên nhiên và đối xử đối với người da đỏ của người da trắng',N'Phê phán lòng tham và sự thiếu ý thức của người da trắng trong việc ứng xử với thiên nhiên',4),
(N'Literature',N'Ý nghĩa giáo dục quan trọng nhất được hướng tới trong văn bản "Động Phong Nha" là gì?',N'Ý thức mở mang hiểu biết',N'Lòng yêu nước và tự hào dân tộc',N'Thói quen tận dụng lợi thế thiên nhiên ban cho',N'Ý thức bảo vệ thiên nhiên và danh lam thắng cảnh',4),
(N'Literature',N'Dòng nào nói đúng nhất tâm trạng của nhân vật người mẹ vào đêm trước ngày khai trường của con trong văn bản "Cổng trường mở ra" (Lí Lan)?',N'Vì mẹ thật sự lo lắng cho buổi khai trường đầu tiên trong cuộc đời của con',N'Vì mẹ đã rất nhiều năm vất vả để nuôi con, giờ thấy ngày mai con được đến trường nên vui quá không thể ngủ được',N'Vì mẹ quá vui sướng bởi đứa con của mình sắp trở thành học sinh lớp Một',N'Vì mẹ hồi hộp, cảm động, tin tưởng, nhớ ngày khai giảng đầu tiên của mình, nghĩ về ngày mai của đứa con',4),
(N'Literature',N'Trong văn bản "Mẹ tôi" (A. Đơ-mi-xi), vì sao người bố lại viết thư cho En-ri-cô?',N'Vì muốn trò chuyện tâm tình về tương lai của En-ri-cô',N'Vì muốn động viên En-ri-cô cố gắng vươn lên trong học tập',N'Vì muốn nhắc nhở và phê bình nghiêm khắc con về hành động thiếu lễ độ với mẹ trước mặt cô giáo',N'Vì nhằm phê bình nghiêm khắc sự lười học của En-ri–cô',3),
(N'Literature',N'Trong văn bản "Mẹ tôi", người bố cho rằng ngày buồn thảm nhất với En-ri-cô là ngày nào?',N'Ngày En-ri-cô không còn mẹ',N'Ngày En-ri-cô bị cô giáo đuổi học',N'Ngày En-ri-cô bị ốm nên phải nằm viện',N'Ngày En-ri-cô không được bố ôm hôn, vì đã hỗn với cô giáo',1),
(N'Literature',N'Ai là nhân vật kể chuyện trong văn bản "Cuộc chia tay của những con búp bê" (Khánh Hoài)?',N'Người mẹ',N'Bé Thủy',N'Người anh (Thành)',N'Cô giáo của Thủy (cô Tâm)',3)

/*
INSERT INTO [NganHangCauHoiToan](NoiDung,TraLoi1,Tra) VALUES
(N'Cơ sở vật chất của hiện tượng di truyền ở cấp độ phân tử là',N'Prôtêin.',N'',N'',N'',),
(N'',N'',N'',N'',N'',),
(N'',N'',N'',N'',N'',),
(N'',N'',N'',N'',N'',),
(N'',N'',N'',N'',N'',),
(N'',N'',N'',N'',N'',),
(N'',N'',N'',N'',N'',),
(N'',N'',N'',N'',N'',),
(N'',N'',N'',N'',N'',),
(N'',N'',N'',N'',N'',),
(N'',N'',N'',N'',N'',),
(N'',N'',N'',N'',N'',),
(N'',N'',N'',N'',N'',),
(N'',N'',N'',N'',N'',),
(N'',N'',N'',N'',N'',),
(N'',N'',N'',N'',N'',),
(N'',N'',N'',N'',N'',),
(N'',N'',N'',N'',N'',),
(N'',N'',N'',N'',N'',),
(N'',N'',N'',N'',N'',)*/

SELECT * FROM GiaoVu WHERE IsDeleted=0
--UPDATE [GiaoVu] SET IsDeleted=1 WHERE TenTaiKhoan='admin'

IF EXISTS (SELECT * FROM sys.views WHERE name='vwListUser')
DROP VIEW vwListUser
GO
CREATE VIEW vwListUser
AS
SELECT TenTaiKhoan,MatKhau
FROM [GiaoVu] WHERE IsDeleted=0
GO

SELECT * FROM vwListUser