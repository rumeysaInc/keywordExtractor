/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import type { Observable } from 'rxjs';
import type { ArticleRequest } from '../models/ArticleRequest';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
@Injectable({
    providedIn: 'root',
})
export class KeywordControllerService {
    constructor(public readonly http: HttpClient) {}
    /**
     * @param requestBody
     * @returns string OK
     * @throws ApiError
     */
    public extractKeywords(
        requestBody: ArticleRequest,
    ): Observable<Array<string>> {
        return __request(OpenAPI, this.http, {
            method: 'POST',
            url: '/api/articles/extractKeywords',
            body: requestBody,
            mediaType: 'application/json',
        });
    }
}
