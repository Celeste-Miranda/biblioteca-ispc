import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TablaLendingsComponent } from './tabla-lendings.component';

describe('TablaLendingsComponent', () => {
  let component: TablaLendingsComponent;
  let fixture: ComponentFixture<TablaLendingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TablaLendingsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TablaLendingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
