/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import type { Observable } from 'rxjs';
import type { AuthResponseDTO } from '../models/AuthResponseDTO';
import type { LoginDto } from '../models/LoginDto';
import type { RegisterDto } from '../models/RegisterDto';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
@Injectable({
    providedIn: 'root',
})
export class UsersControllerService {
    private apiUrl = '/api/auth'; 
    constructor(public readonly http: HttpClient) {}
    /**
     * @param requestBody
     * @returns string OK
     * @throws ApiError
     */

    

    public register(
        requestBody: RegisterDto,
    ): Observable<string> {
        return __request(OpenAPI, this.http, {
            method: 'POST',
            url: '/api/auth/register',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `Bad Request`,
            },
        });
    }
    /**
     * @param requestBody
     * @returns AuthResponseDTO OK
     * @throws ApiError
     */
    public login(
        requestBody: LoginDto,
    ): Observable<AuthResponseDTO> {
        return __request(OpenAPI, this.http, {
            method: 'POST',
            url: '/api/auth/login',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `Bad Request`,
            },
        });
    }
}
