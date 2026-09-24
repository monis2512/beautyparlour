import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Query {
  id?: number;
  name: string;
  mobileNumber: string;
  service: string;
  question: string;
  description: string;
  createdAt?: string;
}

@Injectable({ providedIn: 'root' })
export class ApiService {
  private http = inject(HttpClient);
  private readonly base = '/api';

  create(q: Query): Observable<Query> {
    return this.http.post<Query>(`${this.base}/queries`, q);
  }

  login(username: string, password: string) {
    return this.http.post<{ loggedIn: boolean }>(`${this.base}/admin/login`, { username, password });
  }

  session() {
    return this.http.get<{ loggedIn: boolean }>(`${this.base}/admin/session`);
  }

  queries() {
    return this.http.get<Query[]>(`${this.base}/admin/queries`);
  }

  delete(id: number) {
    return this.http.delete(`${this.base}/admin/queries/${id}`);
  }

  logout() {
    return this.http.post(`${this.base}/admin/logout`, {});
  }
}
