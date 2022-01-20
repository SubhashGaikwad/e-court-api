import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'court-case',
        data: { pageTitle: 'ecourtapiApp.courtCase.home.title' },
        loadChildren: () => import('./court-case/court-case.module').then(m => m.CourtCaseModule),
      },
      {
        path: 'hearing',
        data: { pageTitle: 'ecourtapiApp.hearing.home.title' },
        loadChildren: () => import('./hearing/hearing.module').then(m => m.HearingModule),
      },
      {
        path: 'security-user',
        data: { pageTitle: 'ecourtapiApp.securityUser.home.title' },
        loadChildren: () => import('./security-user/security-user.module').then(m => m.SecurityUserModule),
      },
      {
        path: 'user-access',
        data: { pageTitle: 'ecourtapiApp.userAccess.home.title' },
        loadChildren: () => import('./user-access/user-access.module').then(m => m.UserAccessModule),
      },
      {
        path: 'security-role',
        data: { pageTitle: 'ecourtapiApp.securityRole.home.title' },
        loadChildren: () => import('./security-role/security-role.module').then(m => m.SecurityRoleModule),
      },
      {
        path: 'security-permission',
        data: { pageTitle: 'ecourtapiApp.securityPermission.home.title' },
        loadChildren: () => import('./security-permission/security-permission.module').then(m => m.SecurityPermissionModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
