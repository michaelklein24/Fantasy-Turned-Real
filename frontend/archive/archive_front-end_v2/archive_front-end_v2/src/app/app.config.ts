import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';
import { provideToastr } from 'ngx-toastr';
import { provideAnimations } from '@angular/platform-browser/animations';
import { loggingInterceptor } from './interceptors/logging-interceptor.service';
import { provideCharts, withDefaultRegisterables } from 'ng2-charts';
import { tokenInterceptor } from './interceptors/token-interceptor.service';
import { FullscreenOverlayContainer, Overlay, OverlayContainer, OverlayRef } from '@angular/cdk/overlay';

export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes),
  provideClientHydration(),
  provideHttpClient(withInterceptors([tokenInterceptor, loggingInterceptor]), withFetch()),
  provideCharts(withDefaultRegisterables()),
  provideToastr(),
  provideAnimations()]
};
