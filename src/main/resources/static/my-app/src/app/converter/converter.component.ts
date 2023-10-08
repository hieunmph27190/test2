import { Component } from '@angular/core';
import {HttpClient, HttpHeaders } from'@angular/common/http'
@Component({
  selector: 'app-converter',
  templateUrl: './converter.component.html',
  styleUrls: ['./converter.component.scss']
})
export class ConverterComponent {
  constructor(private http: HttpClient) {}

  ngOnInit() {
    // Gọi API để lấy danh sách các định dạng đầu ra
    this.http.get<any>('/api/datatype').subscribe((data) => {
      this.dataFormats = data;
    });
  }
  currentSelectedInputFormat: string = 'string'
  currentSelectedOutputFormat: string = 'base64'
  selectedInputFormat= "string";
  selectedOutputFormat= "base64";
  inputData = '';
  outputData = '';
  dataFormats: { [key: string]: string } = {

};

  getOutputFormatKeys(): string[] {
    return Object.keys(this.dataFormats);
  }


onSelectChangeOut(event: any) {
  if(this.currentSelectedInputFormat==this.selectedOutputFormat){
    this.selectedInputFormat=  this.currentSelectedOutputFormat;

  }

  this.currentSelectedOutputFormat=this.selectedOutputFormat;
  this.currentSelectedInputFormat=this.selectedInputFormat;
}
onSelectChangeIn(event: any) {
   if(this.selectedInputFormat==this.currentSelectedOutputFormat){
        this.selectedOutputFormat=  this.currentSelectedInputFormat;
  }
  this.currentSelectedOutputFormat=this.selectedOutputFormat;
  this.currentSelectedInputFormat=this.selectedInputFormat;
}
  convertData() {
    let data = {
      data:this.inputData,
      format:this.selectedInputFormat,
      to:this.selectedOutputFormat
    }
    const body = JSON.stringify(data);
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    interface MyResponse {
    data: string;
    status: number;
    error: {
      data:string
    }

    }
    this.http.post<MyResponse>('/api/convert', body, { headers }).subscribe(
    (response) => {
        console.log('API Response:', response);
        this.outputData = response?.data;

    },
    (error) => {
        // Xử lý lỗi ở đây
        console.error('API Error:', error);
        this.outputData = "";
        alert(error.error.data)

    }
);
  }
}
