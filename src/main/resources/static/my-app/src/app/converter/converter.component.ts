import { Component } from '@angular/core';
import {HttpClient, HttpHeaders } from'@angular/common/http'
@Component({
  selector: 'app-converter',
  templateUrl: './converter.component.html',
  styleUrls: ['./converter.component.scss']
})
export class ConverterComponent {
  constructor(private http: HttpClient) {}
  currentSelectedInputFormat: string = 'string'
  currentSelectedOutputFormat: string = 'base64'
  selectedInputFormat= "string";
  selectedOutputFormat= "base64";
  inputData = '';
  outputData = '';


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
