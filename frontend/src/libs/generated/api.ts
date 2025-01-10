/* tslint:disable */
/* eslint-disable */
/**
 * OpenAPI definition
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: v0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


import { Configuration } from './configuration';
import globalAxios, { AxiosPromise, AxiosInstance } from 'axios';
// Some imports not used depending on template conditions
// @ts-ignore
import { DUMMY_BASE_URL, assertParamExists, setApiKeyToObject, setBasicAuthToObject, setBearerAuthToObject, setOAuthToObject, setSearchParams, serializeDataIfNeeded, toPathString, createRequestFunction } from './common';
// @ts-ignore
import { BASE_PATH, COLLECTION_FORMATS, RequestArgs, BaseAPI, RequiredError } from './base';

/**
 * 
 * @export
 * @interface CreateLeagueRequest
 */
export interface CreateLeagueRequest {
    /**
     * 
     * @type {string}
     * @memberof CreateLeagueRequest
     */
    name: string;
    /**
     * 
     * @type {number}
     * @memberof CreateLeagueRequest
     */
    seasonSequence: number;
    /**
     * 
     * @type {Show}
     * @memberof CreateLeagueRequest
     */
    show: Show;
}
/**
 * 
 * @export
 * @interface CreateLeagueResponse
 */
export interface CreateLeagueResponse {
    /**
     * 
     * @type {League}
     * @memberof CreateLeagueResponse
     */
    league?: League;
}
/**
 * 
 * @export
 * @interface CustomErrorResponse
 */
export interface CustomErrorResponse {
    /**
     * 
     * @type {string}
     * @memberof CustomErrorResponse
     */
    errorCode?: string;
    /**
     * 
     * @type {string}
     * @memberof CustomErrorResponse
     */
    errorMsg?: string;
    /**
     * 
     * @type {number}
     * @memberof CustomErrorResponse
     */
    status?: number;
    /**
     * 
     * @type {string}
     * @memberof CustomErrorResponse
     */
    timestamp?: string;
}
/**
 * 
 * @export
 * @interface GetInvitesForLeagueResponse
 */
export interface GetInvitesForLeagueResponse {
    /**
     * 
     * @type {Array<Invite>}
     * @memberof GetInvitesForLeagueResponse
     */
    approved?: Array<Invite>;
    /**
     * 
     * @type {Array<Invite>}
     * @memberof GetInvitesForLeagueResponse
     */
    pending?: Array<Invite>;
    /**
     * 
     * @type {Array<Invite>}
     * @memberof GetInvitesForLeagueResponse
     */
    declined?: Array<Invite>;
}
/**
 * 
 * @export
 * @interface GetLeaguesForUserResponse
 */
export interface GetLeaguesForUserResponse {
    /**
     * 
     * @type {Array<League>}
     * @memberof GetLeaguesForUserResponse
     */
    leagues?: Array<League>;
}
/**
 * 
 * @export
 * @interface Invite
 */
export interface Invite {
    /**
     * 
     * @type {string}
     * @memberof Invite
     */
    leagueId?: string;
    /**
     * 
     * @type {User}
     * @memberof Invite
     */
    invitee?: User;
    /**
     * 
     * @type {User}
     * @memberof Invite
     */
    inviter?: User;
    /**
     * 
     * @type {InviteStatus}
     * @memberof Invite
     */
    status?: InviteStatus;
    /**
     * 
     * @type {string}
     * @memberof Invite
     */
    createTime?: string;
    /**
     * 
     * @type {string}
     * @memberof Invite
     */
    updateTime?: string;
}
/**
 * 
 * @export
 * @enum {string}
 */

export enum InviteStatus {
    Pending = 'PENDING',
    Approved = 'APPROVED',
    Declined = 'DECLINED'
}

/**
 * 
 * @export
 * @interface InviteUserToLeagueRequest
 */
export interface InviteUserToLeagueRequest {
    /**
     * 
     * @type {string}
     * @memberof InviteUserToLeagueRequest
     */
    inviteeEmail?: string;
}
/**
 * 
 * @export
 * @interface InviteUserToLeagueResponse
 */
export interface InviteUserToLeagueResponse {
    /**
     * 
     * @type {Invite}
     * @memberof InviteUserToLeagueResponse
     */
    invite?: Invite;
}
/**
 * 
 * @export
 * @interface League
 */
export interface League {
    /**
     * 
     * @type {string}
     * @memberof League
     */
    leagueId?: string;
    /**
     * 
     * @type {string}
     * @memberof League
     */
    name?: string;
    /**
     * 
     * @type {Array<Participant>}
     * @memberof League
     */
    participants?: Array<Participant>;
    /**
     * 
     * @type {Season}
     * @memberof League
     */
    season?: Season;
    /**
     * 
     * @type {string}
     * @memberof League
     */
    createTime?: string;
}
/**
 * 
 * @export
 * @interface LoginUserRequest
 */
export interface LoginUserRequest {
    /**
     * 
     * @type {string}
     * @memberof LoginUserRequest
     */
    email: string;
    /**
     * 
     * @type {string}
     * @memberof LoginUserRequest
     */
    password: string;
}
/**
 * 
 * @export
 * @interface LoginUserResponse
 */
export interface LoginUserResponse {
    /**
     * 
     * @type {string}
     * @memberof LoginUserResponse
     */
    accessToken?: string;
    /**
     * 
     * @type {string}
     * @memberof LoginUserResponse
     */
    tokenType?: string;
}
/**
 * 
 * @export
 * @interface Participant
 */
export interface Participant {
    /**
     * 
     * @type {string}
     * @memberof Participant
     */
    leagueId?: string;
    /**
     * 
     * @type {string}
     * @memberof Participant
     */
    userId?: string;
    /**
     * 
     * @type {string}
     * @memberof Participant
     */
    role?: ParticipantRoleEnum;
}

/**
    * @export
    * @enum {string}
    */
export enum ParticipantRoleEnum {
    Owner = 'OWNER',
    Admin = 'ADMIN',
    Member = 'MEMBER'
}

/**
 * 
 * @export
 * @interface RegisterUserRequest
 */
export interface RegisterUserRequest {
    /**
     * 
     * @type {string}
     * @memberof RegisterUserRequest
     */
    firstName: string;
    /**
     * 
     * @type {string}
     * @memberof RegisterUserRequest
     */
    lastName: string;
    /**
     * 
     * @type {string}
     * @memberof RegisterUserRequest
     */
    email: string;
    /**
     * 
     * @type {string}
     * @memberof RegisterUserRequest
     */
    password: string;
}
/**
 * 
 * @export
 * @interface RegisterUserResponse
 */
export interface RegisterUserResponse {
    /**
     * 
     * @type {string}
     * @memberof RegisterUserResponse
     */
    accessToken?: string;
    /**
     * 
     * @type {string}
     * @memberof RegisterUserResponse
     */
    tokenType?: string;
}
/**
 * 
 * @export
 * @interface Season
 */
export interface Season {
    /**
     * 
     * @type {number}
     * @memberof Season
     */
    sequence?: number;
    /**
     * 
     * @type {Show}
     * @memberof Season
     */
    show?: Show;
    /**
     * 
     * @type {string}
     * @memberof Season
     */
    startTime?: string;
    /**
     * 
     * @type {string}
     * @memberof Season
     */
    endTime?: string;
    /**
     * 
     * @type {number}
     * @memberof Season
     */
    totalEpisodes?: number;
}
/**
 * 
 * @export
 * @enum {string}
 */

export enum Show {
    Survivor = 'SURVIVOR'
}

/**
 * 
 * @export
 * @interface UpdateInviteStatusRequest
 */
export interface UpdateInviteStatusRequest {
    /**
     * 
     * @type {InviteStatus}
     * @memberof UpdateInviteStatusRequest
     */
    newStatus?: InviteStatus;
}
/**
 * 
 * @export
 * @interface User
 */
export interface User {
    /**
     * 
     * @type {string}
     * @memberof User
     */
    userId?: string;
    /**
     * 
     * @type {string}
     * @memberof User
     */
    firstName?: string;
    /**
     * 
     * @type {string}
     * @memberof User
     */
    lastName?: string;
    /**
     * 
     * @type {string}
     * @memberof User
     */
    email?: string;
}

/**
 * AuthControllerApi - axios parameter creator
 * @export
 */
export const AuthControllerApiAxiosParamCreator = function (configuration?: Configuration) {
    return {
        /**
         * 
         * @summary Login a user
         * @param {LoginUserRequest} loginUserRequest 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        login: async (loginUserRequest: LoginUserRequest, options: any = {}): Promise<RequestArgs> => {
            // verify required parameter 'loginUserRequest' is not null or undefined
            assertParamExists('login', 'loginUserRequest', loginUserRequest)
            const localVarPath = `/auth/login`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'POST', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            localVarHeaderParameter['Content-Type'] = 'application/json';

            setSearchParams(localVarUrlObj, localVarQueryParameter, options.query);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};
            localVarRequestOptions.data = serializeDataIfNeeded(loginUserRequest, localVarRequestOptions, configuration)

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * 
         * @summary Register a user
         * @param {RegisterUserRequest} registerUserRequest 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        register: async (registerUserRequest: RegisterUserRequest, options: any = {}): Promise<RequestArgs> => {
            // verify required parameter 'registerUserRequest' is not null or undefined
            assertParamExists('register', 'registerUserRequest', registerUserRequest)
            const localVarPath = `/auth/register`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'POST', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            localVarHeaderParameter['Content-Type'] = 'application/json';

            setSearchParams(localVarUrlObj, localVarQueryParameter, options.query);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};
            localVarRequestOptions.data = serializeDataIfNeeded(registerUserRequest, localVarRequestOptions, configuration)

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
    }
};

/**
 * AuthControllerApi - functional programming interface
 * @export
 */
export const AuthControllerApiFp = function(configuration?: Configuration) {
    const localVarAxiosParamCreator = AuthControllerApiAxiosParamCreator(configuration)
    return {
        /**
         * 
         * @summary Login a user
         * @param {LoginUserRequest} loginUserRequest 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async login(loginUserRequest: LoginUserRequest, options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<LoginUserResponse>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.login(loginUserRequest, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * 
         * @summary Register a user
         * @param {RegisterUserRequest} registerUserRequest 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async register(registerUserRequest: RegisterUserRequest, options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<RegisterUserResponse>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.register(registerUserRequest, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
    }
};

/**
 * AuthControllerApi - factory interface
 * @export
 */
export const AuthControllerApiFactory = function (configuration?: Configuration, basePath?: string, axios?: AxiosInstance) {
    const localVarFp = AuthControllerApiFp(configuration)
    return {
        /**
         * 
         * @summary Login a user
         * @param {LoginUserRequest} loginUserRequest 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        login(loginUserRequest: LoginUserRequest, options?: any): AxiosPromise<LoginUserResponse> {
            return localVarFp.login(loginUserRequest, options).then((request) => request(axios, basePath));
        },
        /**
         * 
         * @summary Register a user
         * @param {RegisterUserRequest} registerUserRequest 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        register(registerUserRequest: RegisterUserRequest, options?: any): AxiosPromise<RegisterUserResponse> {
            return localVarFp.register(registerUserRequest, options).then((request) => request(axios, basePath));
        },
    };
};

/**
 * AuthControllerApi - object-oriented interface
 * @export
 * @class AuthControllerApi
 * @extends {BaseAPI}
 */
export class AuthControllerApi extends BaseAPI {
    /**
     * 
     * @summary Login a user
     * @param {LoginUserRequest} loginUserRequest 
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof AuthControllerApi
     */
    public login(loginUserRequest: LoginUserRequest, options?: any) {
        return AuthControllerApiFp(this.configuration).login(loginUserRequest, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * 
     * @summary Register a user
     * @param {RegisterUserRequest} registerUserRequest 
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof AuthControllerApi
     */
    public register(registerUserRequest: RegisterUserRequest, options?: any) {
        return AuthControllerApiFp(this.configuration).register(registerUserRequest, options).then((request) => request(this.axios, this.basePath));
    }
}


/**
 * LeagueControllerApi - axios parameter creator
 * @export
 */
export const LeagueControllerApiAxiosParamCreator = function (configuration?: Configuration) {
    return {
        /**
         * 
         * @summary Update Invite Status
         * @param {string} leagueId 
         * @param {UpdateInviteStatusRequest} updateInviteStatusRequest 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        acceptOrRejectLeagueInvite: async (leagueId: string, updateInviteStatusRequest: UpdateInviteStatusRequest, options: any = {}): Promise<RequestArgs> => {
            // verify required parameter 'leagueId' is not null or undefined
            assertParamExists('acceptOrRejectLeagueInvite', 'leagueId', leagueId)
            // verify required parameter 'updateInviteStatusRequest' is not null or undefined
            assertParamExists('acceptOrRejectLeagueInvite', 'updateInviteStatusRequest', updateInviteStatusRequest)
            const localVarPath = `/league/{leagueId}/invite`
                .replace(`{${"leagueId"}}`, encodeURIComponent(String(leagueId)));
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'PUT', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            localVarHeaderParameter['Content-Type'] = 'application/json';

            setSearchParams(localVarUrlObj, localVarQueryParameter, options.query);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};
            localVarRequestOptions.data = serializeDataIfNeeded(updateInviteStatusRequest, localVarRequestOptions, configuration)

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * 
         * @summary Create a new league and assign the owner
         * @param {CreateLeagueRequest} createLeagueRequest 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        createLeague: async (createLeagueRequest: CreateLeagueRequest, options: any = {}): Promise<RequestArgs> => {
            // verify required parameter 'createLeagueRequest' is not null or undefined
            assertParamExists('createLeague', 'createLeagueRequest', createLeagueRequest)
            const localVarPath = `/league`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'POST', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            localVarHeaderParameter['Content-Type'] = 'application/json';

            setSearchParams(localVarUrlObj, localVarQueryParameter, options.query);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};
            localVarRequestOptions.data = serializeDataIfNeeded(createLeagueRequest, localVarRequestOptions, configuration)

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * 
         * @summary Fetch invites for league
         * @param {string} leagueId 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getInvitesForLeague: async (leagueId: string, options: any = {}): Promise<RequestArgs> => {
            // verify required parameter 'leagueId' is not null or undefined
            assertParamExists('getInvitesForLeague', 'leagueId', leagueId)
            const localVarPath = `/league/{leagueId}/invite`
                .replace(`{${"leagueId"}}`, encodeURIComponent(String(leagueId)));
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'GET', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            setSearchParams(localVarUrlObj, localVarQueryParameter, options.query);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * 
         * @summary Fetch Leagues
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getLeaguesForUser: async (options: any = {}): Promise<RequestArgs> => {
            const localVarPath = `/league/user`;
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'GET', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            setSearchParams(localVarUrlObj, localVarQueryParameter, options.query);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
        /**
         * 
         * @summary Invite user to league
         * @param {string} leagueId 
         * @param {InviteUserToLeagueRequest} inviteUserToLeagueRequest 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        inviteUserToLeague: async (leagueId: string, inviteUserToLeagueRequest: InviteUserToLeagueRequest, options: any = {}): Promise<RequestArgs> => {
            // verify required parameter 'leagueId' is not null or undefined
            assertParamExists('inviteUserToLeague', 'leagueId', leagueId)
            // verify required parameter 'inviteUserToLeagueRequest' is not null or undefined
            assertParamExists('inviteUserToLeague', 'inviteUserToLeagueRequest', inviteUserToLeagueRequest)
            const localVarPath = `/league/{leagueId}/invite`
                .replace(`{${"leagueId"}}`, encodeURIComponent(String(leagueId)));
            // use dummy base URL string because the URL constructor only accepts absolute URLs.
            const localVarUrlObj = new URL(localVarPath, DUMMY_BASE_URL);
            let baseOptions;
            if (configuration) {
                baseOptions = configuration.baseOptions;
            }

            const localVarRequestOptions = { method: 'POST', ...baseOptions, ...options};
            const localVarHeaderParameter = {} as any;
            const localVarQueryParameter = {} as any;


    
            localVarHeaderParameter['Content-Type'] = 'application/json';

            setSearchParams(localVarUrlObj, localVarQueryParameter, options.query);
            let headersFromBaseOptions = baseOptions && baseOptions.headers ? baseOptions.headers : {};
            localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};
            localVarRequestOptions.data = serializeDataIfNeeded(inviteUserToLeagueRequest, localVarRequestOptions, configuration)

            return {
                url: toPathString(localVarUrlObj),
                options: localVarRequestOptions,
            };
        },
    }
};

/**
 * LeagueControllerApi - functional programming interface
 * @export
 */
export const LeagueControllerApiFp = function(configuration?: Configuration) {
    const localVarAxiosParamCreator = LeagueControllerApiAxiosParamCreator(configuration)
    return {
        /**
         * 
         * @summary Update Invite Status
         * @param {string} leagueId 
         * @param {UpdateInviteStatusRequest} updateInviteStatusRequest 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async acceptOrRejectLeagueInvite(leagueId: string, updateInviteStatusRequest: UpdateInviteStatusRequest, options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<InviteUserToLeagueResponse>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.acceptOrRejectLeagueInvite(leagueId, updateInviteStatusRequest, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * 
         * @summary Create a new league and assign the owner
         * @param {CreateLeagueRequest} createLeagueRequest 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async createLeague(createLeagueRequest: CreateLeagueRequest, options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<CreateLeagueResponse>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.createLeague(createLeagueRequest, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * 
         * @summary Fetch invites for league
         * @param {string} leagueId 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async getInvitesForLeague(leagueId: string, options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<GetInvitesForLeagueResponse>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.getInvitesForLeague(leagueId, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * 
         * @summary Fetch Leagues
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async getLeaguesForUser(options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<GetLeaguesForUserResponse>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.getLeaguesForUser(options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
        /**
         * 
         * @summary Invite user to league
         * @param {string} leagueId 
         * @param {InviteUserToLeagueRequest} inviteUserToLeagueRequest 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        async inviteUserToLeague(leagueId: string, inviteUserToLeagueRequest: InviteUserToLeagueRequest, options?: any): Promise<(axios?: AxiosInstance, basePath?: string) => AxiosPromise<InviteUserToLeagueResponse>> {
            const localVarAxiosArgs = await localVarAxiosParamCreator.inviteUserToLeague(leagueId, inviteUserToLeagueRequest, options);
            return createRequestFunction(localVarAxiosArgs, globalAxios, BASE_PATH, configuration);
        },
    }
};

/**
 * LeagueControllerApi - factory interface
 * @export
 */
export const LeagueControllerApiFactory = function (configuration?: Configuration, basePath?: string, axios?: AxiosInstance) {
    const localVarFp = LeagueControllerApiFp(configuration)
    return {
        /**
         * 
         * @summary Update Invite Status
         * @param {string} leagueId 
         * @param {UpdateInviteStatusRequest} updateInviteStatusRequest 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        acceptOrRejectLeagueInvite(leagueId: string, updateInviteStatusRequest: UpdateInviteStatusRequest, options?: any): AxiosPromise<InviteUserToLeagueResponse> {
            return localVarFp.acceptOrRejectLeagueInvite(leagueId, updateInviteStatusRequest, options).then((request) => request(axios, basePath));
        },
        /**
         * 
         * @summary Create a new league and assign the owner
         * @param {CreateLeagueRequest} createLeagueRequest 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        createLeague(createLeagueRequest: CreateLeagueRequest, options?: any): AxiosPromise<CreateLeagueResponse> {
            return localVarFp.createLeague(createLeagueRequest, options).then((request) => request(axios, basePath));
        },
        /**
         * 
         * @summary Fetch invites for league
         * @param {string} leagueId 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getInvitesForLeague(leagueId: string, options?: any): AxiosPromise<GetInvitesForLeagueResponse> {
            return localVarFp.getInvitesForLeague(leagueId, options).then((request) => request(axios, basePath));
        },
        /**
         * 
         * @summary Fetch Leagues
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        getLeaguesForUser(options?: any): AxiosPromise<GetLeaguesForUserResponse> {
            return localVarFp.getLeaguesForUser(options).then((request) => request(axios, basePath));
        },
        /**
         * 
         * @summary Invite user to league
         * @param {string} leagueId 
         * @param {InviteUserToLeagueRequest} inviteUserToLeagueRequest 
         * @param {*} [options] Override http request option.
         * @throws {RequiredError}
         */
        inviteUserToLeague(leagueId: string, inviteUserToLeagueRequest: InviteUserToLeagueRequest, options?: any): AxiosPromise<InviteUserToLeagueResponse> {
            return localVarFp.inviteUserToLeague(leagueId, inviteUserToLeagueRequest, options).then((request) => request(axios, basePath));
        },
    };
};

/**
 * LeagueControllerApi - object-oriented interface
 * @export
 * @class LeagueControllerApi
 * @extends {BaseAPI}
 */
export class LeagueControllerApi extends BaseAPI {
    /**
     * 
     * @summary Update Invite Status
     * @param {string} leagueId 
     * @param {UpdateInviteStatusRequest} updateInviteStatusRequest 
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof LeagueControllerApi
     */
    public acceptOrRejectLeagueInvite(leagueId: string, updateInviteStatusRequest: UpdateInviteStatusRequest, options?: any) {
        return LeagueControllerApiFp(this.configuration).acceptOrRejectLeagueInvite(leagueId, updateInviteStatusRequest, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * 
     * @summary Create a new league and assign the owner
     * @param {CreateLeagueRequest} createLeagueRequest 
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof LeagueControllerApi
     */
    public createLeague(createLeagueRequest: CreateLeagueRequest, options?: any) {
        return LeagueControllerApiFp(this.configuration).createLeague(createLeagueRequest, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * 
     * @summary Fetch invites for league
     * @param {string} leagueId 
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof LeagueControllerApi
     */
    public getInvitesForLeague(leagueId: string, options?: any) {
        return LeagueControllerApiFp(this.configuration).getInvitesForLeague(leagueId, options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * 
     * @summary Fetch Leagues
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof LeagueControllerApi
     */
    public getLeaguesForUser(options?: any) {
        return LeagueControllerApiFp(this.configuration).getLeaguesForUser(options).then((request) => request(this.axios, this.basePath));
    }

    /**
     * 
     * @summary Invite user to league
     * @param {string} leagueId 
     * @param {InviteUserToLeagueRequest} inviteUserToLeagueRequest 
     * @param {*} [options] Override http request option.
     * @throws {RequiredError}
     * @memberof LeagueControllerApi
     */
    public inviteUserToLeague(leagueId: string, inviteUserToLeagueRequest: InviteUserToLeagueRequest, options?: any) {
        return LeagueControllerApiFp(this.configuration).inviteUserToLeague(leagueId, inviteUserToLeagueRequest, options).then((request) => request(this.axios, this.basePath));
    }
}


